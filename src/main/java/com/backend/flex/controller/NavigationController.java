package com.backend.flex.controller;

import com.backend.flex.model.Website;
import com.backend.flex.model.navigation.Navigation;
import com.backend.flex.model.navigation.NavigationCreate;
import com.backend.flex.model.navigation.NavigationLinks;
import com.backend.flex.model.navigation.NavigationUpdate;
import com.backend.flex.security.services.UserDetailsImpl;
import com.backend.flex.service.LinkService;
import com.backend.flex.service.NavigationService;
import com.backend.flex.service.WebsiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller class for handling HTTP requests related to navigation.
 * This class defines endpoints for retrieving, creating, updating, and deleting navigation items and navigation links.
 */

@CrossOrigin(origins = "http://localhost:4200", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
@RestController
@RequestMapping("/api/navigation")
public class NavigationController {

    private final NavigationService navigationService;
    private final WebsiteService websiteService;
    private final LinkService linkService;

    /**
     * Constructs a NavigationController object with the specified services.
     *
     * @param navigationService the service responsible for managing navigation items
     * @param linkService the service responsible for managing links
     */
    public NavigationController(NavigationService navigationService, WebsiteService websiteService, LinkService linkService) {
        this.navigationService = navigationService;
        this.websiteService = websiteService;
        this.linkService = linkService;
    }

    /**
     * Handles GET requests to retrieve a navigation item by its ID.
     *
     * @param id the ID of the navigation item to retrieve
     * @return a ResponseEntity containing the requested Navigation if found, or NOT_FOUND if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Navigation> getNavigation(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl user){
        Optional<Navigation> nav = navigationService.getNavigationById(id);
        user.validateAccess(nav.orElseThrow().getWebsite());
        return nav.map(navigation -> new ResponseEntity<>(navigation, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.OK));
    }

    /**
     * Handles POST requests to create a new navigation item.
     *
     * @param nav the data of the navigation item to create
     * @return a ResponseEntity containing the created Navigation if successful, or INTERNAL_SERVER_ERROR if an exception occurs
     */
    @PostMapping
    public ResponseEntity<Navigation> createNavigation(@RequestBody NavigationCreate nav, @AuthenticationPrincipal UserDetailsImpl user){
        try {
            Website website = websiteService.getWebsiteById(nav.getWebsite()).orElseThrow();
            user.validateAccess(website);
            return new ResponseEntity<>( navigationService.createNavigation(nav), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Handles PUT requests to update a navigation item by its ID.
     *
     * @param id the ID of the navigation item to update
     * @param navigation the updated Navigation data
     * @return a ResponseEntity containing the updated Navigation if successful, or NOT_FOUND if the navigation item is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Navigation> updateNavigation(@PathVariable long id,@RequestBody NavigationUpdate navigation, @AuthenticationPrincipal UserDetailsImpl user){
        Optional<Navigation> oldNav = navigationService.getNavigationById(id);
        if (oldNav.isPresent()) {
            user.validateAccess(oldNav.get().getWebsite());
            return  new ResponseEntity<>(navigationService.updateNavigation(oldNav.get(), navigation), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handles DELETE requests to delete a navigation item by its ID.
     *
     * @param id the ID of the navigation item to delete
     * @return a ResponseEntity with OK if successful, or INTERNAL_SERVER_ERROR if an exception occurs
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteNavigation(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetailsImpl user){
        try {
            Navigation nav = navigationService.getNavigationById(id).orElseThrow();
            user.validateAccess(nav.getWebsite());
            navigationService.deleteNavigation(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
