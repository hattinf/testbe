package com.backend.flex.controller;

import com.backend.flex.model.Website;
import com.backend.flex.model.footer.Footer;
import com.backend.flex.model.footer.FooterCreate;
import com.backend.flex.model.footer.FooterLinks;
import com.backend.flex.model.footer.FooterUpdate;
import com.backend.flex.model.navigation.NavigationLinks;
import com.backend.flex.security.services.UserDetailsImpl;
import com.backend.flex.service.FooterService;
import com.backend.flex.service.LinkService;
import com.backend.flex.service.WebsiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller class for handling HTTP requests related to the footer.
 * This class defines endpoints for retrieving, creating, updating, and deleting footers and footer links.
 */

@CrossOrigin(origins = "http://localhost:4200", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
@RestController
@RequestMapping("/api/footer")
public class FooterController {

    private final FooterService footerService;
    private final LinkService linkService;
    private final WebsiteService websiteService;

    /**
     * Constructs a FooterController object with the specified services.
     *
     * @param footerService the service responsible for managing footers
     * @param linkService the service responsible for managing links
     */
    public FooterController(FooterService footerService, LinkService linkService, WebsiteService websiteService) {
        this.footerService = footerService;
        this.linkService = linkService;
        this.websiteService = websiteService;
    }

    /**
     * Handles GET requests to retrieve a footer by its ID.
     *
     * @param id the ID of the footer to retrieve
     * @return a ResponseEntity containing the requested Footer if found, or NOT_FOUND if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Footer> getFooter(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl user){
        Optional<Footer> fot = footerService.getFooterById(id);
        user.validateAccess(fot.orElseThrow().getWebsite());
        return fot.map(footer -> new ResponseEntity<>(footer, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    /**
     * Handles POST requests to create a new footer.
     *
     * @param fot the data of the footer to create
     * @return a ResponseEntity containing the created Footer if successful, or INTERNAL_SERVER_ERROR if an exception occurs
     */
    @PostMapping
    public ResponseEntity<Footer> createFooter(@RequestBody FooterCreate fot, @AuthenticationPrincipal UserDetailsImpl user){
        Website website = websiteService.getWebsiteById(fot.getWebsite()).orElseThrow();
        user.validateAccess(website);
        return new ResponseEntity<>( footerService.createFooter(fot), HttpStatus.OK);
    }

    /**
     * Handles PUT requests to update a footer by its ID.
     *
     * @param id the ID of the footer to update
     * @param footer the updated Footer data
     * @return a ResponseEntity containing the updated Footer if successful, or NOT_FOUND if the footer is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Footer> updateFooter(@PathVariable long id,@RequestBody FooterUpdate footer, @AuthenticationPrincipal UserDetailsImpl user){
        Optional<Footer> oldFooter = footerService.getFooterById(id);
        if (oldFooter.isPresent()) {
            user.validateAccess(oldFooter.get().getWebsite());
            return  new ResponseEntity<>(footerService.updateFooter(oldFooter.get(), footer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handles DELETE requests to delete a footer by its ID.
     *
     * @param id the ID of the footer to delete
     * @return a ResponseEntity with OK if successful, or NOT_FOUND if the footer is not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFooter(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetailsImpl user){
        Optional<Footer> footer = footerService.getFooterById(id);
        if(footer.isPresent()){
            user.validateAccess(footer.get().getWebsite());
            footerService.deleteFooter(id);
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
