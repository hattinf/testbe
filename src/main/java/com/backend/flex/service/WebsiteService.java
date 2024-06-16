package com.backend.flex.service;

import com.backend.flex.model.Page;
import com.backend.flex.model.Website;
import com.backend.flex.repository.WebsiteRepository;
import com.backend.flex.security.services.UserDetailsImpl;
import com.backend.flex.security.services.UserDetailsServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing website-related operations.
 * This class provides methods for CRUD operations on website entities.
 */
@Service
public class WebsiteService {

    @Autowired
    WebsiteRepository websiteRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    /**
     * Retrieves all websites.
     *
     * @return a list of all websites
     */
    public List<Website> getAllWebsites() {
        return websiteRepository.findAll();
    }

    public List<Website> getAllWebsitesByUser(Long id) {
        return websiteRepository.findByUserId(id);
    }

    /**
     * Retrieves a website by its ID.
     *
     * @param id the ID of the website to retrieve
     * @return an Optional containing the requested website, or empty if not found
     */
    public Optional<Website> getWebsiteById(Long id) {
        return websiteRepository.findById(id);
    }

    /**
     * Deletes a website by its ID.
     *
     * @param id the ID of the website to delete
     */
    public void deleteWebsiteById(long id) {
        websiteRepository.deleteById(id);
    }

    /**
     * Updates an existing website by its ID.
     *
     * @param websiteData the Optional containing the existing website data
     * @param updateData the updated website data
     * @return the updated website
     */
    @Transactional
    public Website updateWebsiteById(Optional<Website> websiteData, Website updateData){
        Website _website = websiteData.get();
        _website.setName(updateData.getName());
        _website.setDescription(updateData.getDescription());
        return websiteRepository.save(_website);
    }

    /**
     * Publish an existing website by its ID.
     *
     * @param websiteData the Optional containing the existing website data
     * @return the updated website
     */
    public Website publishWebsiteById(Optional<Website> websiteData){
        Website _website = websiteData.get();
        _website.setPublished(!_website.getPublished());
        return websiteRepository.save(_website);
    }

    /**
     * Creates a new website.
     *
     * @param website the data of the website to create
     * @return the created website
     */
    public Website createWebsite(Website website, UserDetailsImpl user) {
        return websiteRepository.save(new Website(website.getName(), userDetailsService.loadUserByUsernameSystem(user.getUsername())));
    }

    /**
     * Return a page from list based on the URL path. This Function is left without error handling to return null in case url is wrong, this
     * is done to make sure a Frontend can still correctly load such Components like Footer and Navigation,
     * while still able to handle 404 Not found error.
     *
     * @param url requested path of the page
     * @param data list of Websites Pages
     * @return the created website
     */
    public Optional<Page> getWebsitePageByUrl(String url, List<Page> data) {
        if (url == null) {
            return data.stream().filter(obj -> obj.getSlug().equals("/")).findFirst();
        } else {
            String[] slugs = url.split("/");
            Optional<Page> finalPage = Optional.empty();

            for (int i = 0; i < slugs.length; i++) {
                String slug = "/" + slugs[i];
                Optional<Page> matchingPage = data.stream().filter(obj -> obj.getSlug().equals(slug)).findFirst();

                if (matchingPage.isPresent()) {
                    if (i == slugs.length - 1) {
                        finalPage = matchingPage;
                    } else {
                        data = matchingPage.get().getPage();
                    }
                }
            }
            return finalPage;
        }
    }
}
