package com.backend.flex.service;

import com.backend.flex.model.Page;
import com.backend.flex.model.Website;
import com.backend.flex.model.footer.Footer;
import com.backend.flex.model.footer.FooterCreate;
import com.backend.flex.model.footer.FooterLinks;
import com.backend.flex.model.footer.FooterUpdate;
import com.backend.flex.model.navigation.Navigation;
import com.backend.flex.model.navigation.NavigationLinks;
import com.backend.flex.repository.FooterLinkRepository;
import com.backend.flex.repository.FooterRepository;
import com.backend.flex.repository.NavigationLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing footer-related operations.
 * This class provides methods for CRUD operations on footer entities.
 */
@Service
public class FooterService {
    private final WebsiteService websiteService;

    @Autowired
    private FooterRepository footerRepository;

    @Autowired
    private FooterLinkRepository footerLinkRepository;

    /**
     * Constructs a new FooterService with the provided WebsiteService dependency.
     *
     * @param websiteService the WebsiteService dependency
     */
    public FooterService(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    /**
     * Retrieves a footer by its ID.
     *
     * @param id the ID of the footer to retrieve
     * @return an Optional containing the requested footer, or empty if not found
     */
    public Optional<Footer> getFooterById(Long id){
        return footerRepository.findById(id);
    }

    /**
     * Creates a new footer.
     *
     * @param fot the data of the footer to create
     * @return the created footer
     * @throws ResponseStatusException if the associated website is not found
     */
    public Footer createFooter(FooterCreate fot){
        Optional<Website> website = websiteService.getWebsiteById(fot.getWebsite());
        if (website.isPresent()){
            List<Page> pages = website.get().getPage();
            Footer newFot = new Footer(fot.getBottomText(), fot.getBackgroundColor(), fot.getLinkColor(), website.get());
            Footer foot_ = footerRepository.save(newFot);

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
                    FooterLinks link_ = new FooterLinks(page.getTitle(), combinedSlug.toString(), foot_, page);
                    footerLinkRepository.save(link_);
                } else {
                    FooterLinks link_ = new FooterLinks(page.getTitle(), page.getSlug(), foot_, page);
                    footerLinkRepository.save(link_);
                }
            }

            return foot_;
        } else  {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Updates an existing footer.
     *
     * @param old the existing footer to update
     * @param newNav the updated footer data
     * @return the updated footer
     */
    public Footer updateFooter(Footer old, FooterUpdate newNav){
        old.setBottomText(newNav.getBottomText());
        old.setBackgroundColor(newNav.getBackgroundColor());
        old.setLinkColor(newNav.getLinkColor());
        return  footerRepository.save(old);
    }

    /**
     * Deletes a footer by its ID.
     *
     * @param id the ID of the footer to delete
     */
    public void deleteFooter(Long id){
        footerRepository.deleteById(id);
    }
}
