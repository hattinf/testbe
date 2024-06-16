package com.backend.flex.controller;

import com.backend.flex.model.Page;
import com.backend.flex.model.Website;
import com.backend.flex.model.WebsitePublicOutput;
import com.backend.flex.model.components.Components;
import com.backend.flex.model.components.ComponentsCreate;
import com.backend.flex.model.components.props.HeroProps;
import com.backend.flex.model.website.WebsiteShort;
import com.backend.flex.security.services.UserDetailsImpl;
import com.backend.flex.security.services.UserDetailsServiceImpl;
import com.backend.flex.service.FooterService;
import com.backend.flex.service.PageService;
import com.backend.flex.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for handling HTTP requests related to websites.
 * This class defines endpoints for retrieving, creating, updating, and deleting websites and their pages.
 */

@CrossOrigin(origins = "http://localhost:4200", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
@RestController
@RequestMapping("/api/websites")
public class WebsiteController {

    private final WebsiteService websiteService;
    private  final PageService pageService;
    private  final  PageController pageController;
    private final FooterService footerService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    /**
     * Constructs a WebsiteController object with the specified services.
     *
     * @param websiteService the service responsible for managing websites
     * @param pageService the service responsible for managing pages
     */
    public WebsiteController(WebsiteService websiteService, PageService pageService, PageController pageController, FooterService footerService){
        this.websiteService = websiteService;
        this.pageService = pageService;
        this.pageController = pageController;
        this.footerService = footerService;
    }

    /**
     * Handles GET requests to retrieve all websites.
     *
     * @return a ResponseEntity containing a list of websites if successful, or INTERNAL_SERVER_ERROR if an exception occurs
     */
    @GetMapping()
    public ResponseEntity<List<Website>> getAllWebsites( @AuthenticationPrincipal UserDetailsImpl user) {
            if (user.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                return new ResponseEntity<>(websiteService.getAllWebsites(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(websiteService.getAllWebsitesByUser(user.getId()), HttpStatus.OK);
            }
    }

    /**
     * Handles GET requests to retrieve a website by its ID.
     *
     * @param id the ID of the website to retrieve
     * @return a ResponseEntity containing the requested website if found, or NOT_FOUND if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Website> getWebsiteById(@PathVariable("id") long id, @AuthenticationPrincipal UserDetailsImpl user) {
        Optional<Website> websiteData = websiteService.getWebsiteById(id);
        if (websiteData.isPresent()) {
            user.validateAccess(websiteData.get());
            return new ResponseEntity<>(websiteData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handles GET requests to retrieve all public websites.
     * @return a ResponseEntity containing a list of public websites if successful, or INTERNAL_SERVER_ERROR if an exception occurs
     */
    @GetMapping("/public")
    public ResponseEntity<List<WebsiteShort>> getAllWebsitesPublic() {
        List<Website> _web = websiteService.getAllWebsites();
        List<WebsiteShort> _webShort = _web.stream().filter(Website::getPublished).map(website ->
                new WebsiteShort(website.getId(), website.getName(), website.getDescription())
        ).toList();
        return new ResponseEntity<>(_webShort, HttpStatus.OK);
    }

    /**
     * Handles GET requests to retrieve a public website by its ID and request page path.
     *
     * @param id   the ID of the website to retrieve
     * @param user
     * @return a ResponseEntity containing the requested website if found, or NOT_FOUND if not found
     */
    @GetMapping("/public/{id}")
    public ResponseEntity<WebsitePublicOutput> getWebsiteByIdByPage(@PathVariable("id") long id, @RequestParam(required = false) String url, UserDetailsImpl user) {
        Optional<Website> websiteData = websiteService.getWebsiteById(id);
        if (websiteData.isPresent()) {

            if (!websiteData.get().getPublished()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<Page> data = websiteData.get().getPage();
            Optional<Page> selectedPage = websiteService.getWebsitePageByUrl(url, data);

            WebsitePublicOutput output = new WebsitePublicOutput(
                    websiteData.get().getId(),
                    websiteData.get().getName(),
                    selectedPage.isPresent() ? selectedPage.get().getHidden() ? null : selectedPage.get() : null,
                    websiteData.get().getFooter(),
                    websiteData.get().getNavigation(), url);

            return new ResponseEntity<>(output,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handles POST requests to create a new website.
     *
     * @param website the data of the website to create
     * @return a ResponseEntity containing the created website if successful, or INTERNAL_SERVER_ERROR if an exception occurs
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Website> createWebsite(@RequestBody Website website, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            Website _website =  websiteService.createWebsite(website, user);
            Page _page = pageService.createPage(new Page("/", "home", false, _website), Optional.empty());
            return new ResponseEntity<>(_website, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Handles POST requests to create a new page for a website.
     *
     * @param websiteId the ID of the website to associate the page with
     * @param pageId the optional ID of the page to clone
     * @param page the data of the page to create
     * @return a ResponseEntity containing the created page if successful, or NOT_FOUND if the website is not found
     */
    @PostMapping("/{websiteId}/page")
    public  ResponseEntity<Page> createPage(@PathVariable Long websiteId, @RequestParam Optional<Long> pageId,  @RequestBody Page page, @AuthenticationPrincipal UserDetailsImpl user){
        Optional<Website> web = websiteService.getWebsiteById(websiteId);
        if (web.isPresent()) {
            user.validateAccess(web.get());
            List<Page> pages = pageService.getWebsitePages(web.get(), page.getSlug());
            if(!pages.isEmpty()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page with slug already exists");
            }
            page.setWebsite(web.get());
            return new ResponseEntity<>(pageService.createPage(page, pageId), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handles PUT requests to update a website by its ID.
     *
     * @param id the ID of the website to update
     * @param website the updated website data
     * @return a ResponseEntity containing the updated website if successful, or NOT_FOUND if the website is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Website> updateWebsite(@PathVariable long id, @RequestBody Website website, @AuthenticationPrincipal UserDetailsImpl user){
        Optional<Website>  websiteData = websiteService.getWebsiteById(id);
        if(websiteData.isPresent()){
            user.validateAccess(websiteData.get());
            Website _website = websiteService.updateWebsiteById(websiteData, website);
            return  new ResponseEntity<>(_website, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handles PUT requests to update a website by its ID.
     *
     * @param id the ID of the website to update
     * @return a ResponseEntity containing the updated website if successful, or NOT_FOUND if the website is not found
     */
    @PutMapping("/{id}/publish")
    public ResponseEntity<Website> publishWebsiteToggle(@PathVariable long id, @AuthenticationPrincipal UserDetailsImpl user){
        Optional<Website>  websiteData = websiteService.getWebsiteById(id);
        if(websiteData.isPresent()){
            user.validateAccess(websiteData.get());
            Website _website = websiteService.publishWebsiteById(websiteData);
            return  new ResponseEntity<>(_website, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handles DELETE requests to delete a website by its ID.
     *
     * @param id the ID of the website to delete
     * @return a ResponseEntity with OK if successful, or INTERNAL_SERVER_ERROR if an exception occurs
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteWebsite(@PathVariable long id, @AuthenticationPrincipal UserDetailsImpl user) {
        Optional<Website>  websiteData = websiteService.getWebsiteById(id);
        if(websiteData.isPresent()){
            user.validateAccess(websiteData.get());
            websiteService.deleteWebsiteById(id);
            return  new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
