package com.backend.flex.service;

import com.backend.flex.model.Page;
import com.backend.flex.model.Website;
import com.backend.flex.model.navigation.Navigation;
import com.backend.flex.model.navigation.NavigationCreate;
import com.backend.flex.model.navigation.NavigationLinks;
import com.backend.flex.model.navigation.NavigationUpdate;
import com.backend.flex.repository.NavigationLinkRepository;
import com.backend.flex.repository.NavigationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing navigation-related operations.
 * This class provides methods for CRUD operations on navigation entities.
 */
@Service
public class NavigationService {

    private final WebsiteService websiteService;

    @Autowired
    private NavigationRepository navigationRepository;
    @Autowired
    private NavigationLinkRepository navigationLinkRepository;

    /**
     * Constructs a new NavigationService with the provided WebsiteService dependency.
     *
     * @param websiteService the WebsiteService dependency
     */
    public NavigationService(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    /**
     * Retrieves a navigation by its ID.
     *
     * @param id the ID of the navigation to retrieve
     * @return an Optional containing the requested navigation, or empty if not found
     */
    public Optional<Navigation> getNavigationById(Long id){
        return navigationRepository.findById(id);
    }

    /**
     * Creates a new navigation.
     *
     * @param nav the data of the navigation to create
     * @return the created navigation
     * @throws ResponseStatusException if the associated website is not found
     */
    public Navigation createNavigation(NavigationCreate nav){
        Optional<Website> website = websiteService.getWebsiteById(nav.getWebsite());
        if (website.isPresent()){
            Navigation newNav = new Navigation(nav.getLogo(), "bg-white", "text-blue-500", website.get());
            Navigation nav_ = navigationRepository.save(newNav);
            List<Page> pages = website.get().getPage();

            for (Page page : pages) {
                if(page.getSub()){
                    boolean loop = true;
                    ArrayList<String> slugs = new ArrayList<>();
                    Page current = page;

                    do {
                        String slug = current.getSlug();
                        slugs.add(slug);
                        if(current.getSub()){
                            current = current.getParentPage();
                        } else {
                            loop = false;
                        }
                    } while (loop);

                    StringBuilder combinedSlug = new StringBuilder();
                    Collections.reverse(slugs);

                    for (String slug : slugs) {
                        combinedSlug.append(slug);
                    }
                    NavigationLinks link_ = new NavigationLinks(page.getTitle(), combinedSlug.toString(), nav_, page);
                    navigationLinkRepository.save(link_);
                } else {
                    NavigationLinks link_ = new NavigationLinks(page.getTitle(), page.getSlug(), nav_, page);
                    navigationLinkRepository.save(link_);
                }
            }
            return nav_;
        } else  {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Updates an existing navigation.
     *
     * @param old the existing navigation to update
     * @param newNav the updated navigation data
     * @return the updated navigation
     */
    public Navigation updateNavigation(Navigation old, NavigationUpdate newNav){
        old.setLogo(newNav.getLogo());
        old.setBackgroundColor(newNav.getBackgroundColor());
        old.setLinkColor(newNav.getLinkColor());
        return  navigationRepository.save(old);
    }

    /**
     * Deletes a navigation by its ID.
     *
     * @param id the ID of the navigation to delete
     */
    public void deleteNavigation(Long id){
        System.out.println("Deleting navigation with ID: " + id);
        navigationRepository.deleteById(id);
    }
}
