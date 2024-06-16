package com.backend.flex.controller;

import com.backend.flex.model.Website;
import com.backend.flex.model.components.Components;
import com.backend.flex.model.components.ComponentsCreate;
import com.backend.flex.model.Page;
import com.backend.flex.model.components.props.Prop;
import com.backend.flex.security.services.UserDetailsImpl;
import com.backend.flex.service.ComponentService;
import com.backend.flex.service.PageService;
import com.backend.flex.service.WebsiteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for handling HTTP requests related to pages.
 * This class defines endpoints for retrieving, creating, updating, and deleting pages and their components.
 */

@CrossOrigin(origins = "http://localhost:4200", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
@RestController
@RequestMapping("/api/pages")
public class PageController {

    private final PageService pageService;
    private final WebsiteService websiteService;
    private final ComponentService componentService;
    private final ObjectMapper mapper;

    /**
     * Constructs a PageController object with the specified services and ObjectMapper.
     *
     * @param pageService the service responsible for managing pages
     * @param componentService the service responsible for managing components
     * @param mapper the ObjectMapper for mapping JSON to objects
     */
    public PageController(PageService pageService, WebsiteService websiteService, ComponentService componentService, ObjectMapper mapper){
        this.pageService = pageService;
        this.websiteService = websiteService;
        this.componentService = componentService;
        this.mapper = mapper;
    }

    /**
     * Handles GET requests to retrieve all pages by website.
     *
     * @return a ResponseEntity containing a list of pages if successful, or INTERNAL_SERVER_ERROR if an exception occurs
     */
    @GetMapping()
    public ResponseEntity<List<Page>> getAllPagesByWebsite(@RequestParam long websiteID, @RequestParam(required = false) String slug,  @AuthenticationPrincipal UserDetailsImpl user){
        Optional<Website> website = websiteService.getWebsiteById(websiteID);
        if(website.isPresent()){
            user.validateAccess(website.get());
            return new ResponseEntity<>(pageService.getWebsitePages(website.get(), slug), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Handles GET requests to retrieve a page by its ID.
     *
     * @param id the ID of the page to retrieve
     * @return a ResponseEntity containing the requested page if found, or NOT_FOUND if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Page> getPageById(@PathVariable("id") long id,   @AuthenticationPrincipal UserDetailsImpl user) {
        Optional<Page> pageData = pageService.getPageById(id);
        if (pageData.isPresent()) {
            user.validateAccess(pageData.get().getWebsite());
            return new ResponseEntity<>(pageData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handles POST requests to create a new component for a page.
     *
     * @param component the data of the component to create
     * @return a ResponseEntity containing the created component if successful, or NOT_FOUND if the page or component type is not found
     */
    @PostMapping("/{id}/component")
    public ResponseEntity<Components> creatNewPageComponent(@RequestBody ComponentsCreate component, @AuthenticationPrincipal UserDetailsImpl user) {
        Optional<Page>  pageData = pageService.getPageById(component.getPage());
        if (pageData.isPresent()) {
            user.validateAccess(pageData.get().getWebsite());
            Prop prop = componentService.createComponentProp(component.getType(), component.getProps(), mapper);
            return new ResponseEntity<>(componentService.createComponents(component, pageData.get(), prop), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handles PUT requests to update a page by its ID.
     *
     * @param id the ID of the page to update
     * @param page the updated page data
     * @return a ResponseEntity containing the updated page if successful, or NOT_FOUND if the page is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Page> updatePage(@PathVariable long id, @RequestBody Page page, @AuthenticationPrincipal UserDetailsImpl user){
        Optional<Page>  pageData = pageService.getPageById(id);
        if(pageData.isPresent()){
            user.validateAccess(pageData.get().getWebsite());
            Page _page = pageService.updatePageById(pageData, page);
            return  new ResponseEntity<>(_page, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handles PUT requests to Hide a page by its ID.
     *
     * @param id the ID of the page to update
     * @return a ResponseEntity containing the updated page if successful, or NOT_FOUND if the page is not found
     */
    @PutMapping("/{id}/hide")
    public ResponseEntity<Page> hidePageToggle(@PathVariable long id, @AuthenticationPrincipal UserDetailsImpl user){
        Optional<Page>  pageData = pageService.getPageById(id);
        if(pageData.isPresent()){
            user.validateAccess(pageData.get().getWebsite());
            Page _page = pageService.togglePublishPageById(pageData);
            return  new ResponseEntity<>(_page, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handles DELETE requests to delete a page by its ID.
     *
     * @param id the ID of the page to delete
     * @return a ResponseEntity with OK if successful, or INTERNAL_SERVER_ERROR if an exception occurs
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePage(@PathVariable("id") long id, @AuthenticationPrincipal UserDetailsImpl user) {
        Optional<Page> page = pageService.getPageById(id);
        if(page.isPresent()){
            user.validateAccess(page.get().getWebsite());
            pageService.deletePageById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
