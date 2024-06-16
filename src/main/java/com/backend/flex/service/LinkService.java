package com.backend.flex.service;

import com.backend.flex.model.footer.Footer;
import com.backend.flex.model.footer.FooterLinks;
import com.backend.flex.model.navigation.Navigation;
import com.backend.flex.model.navigation.NavigationLinks;
import com.backend.flex.repository.FooterLinkRepository;
import com.backend.flex.repository.NavigationLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Service class for managing link-related operations.
 * This class provides methods for creating and deleting links for footer and navigation entities.
 */
@Service
public class LinkService {

    @Autowired
    private FooterLinkRepository footerLinkRepository;

    @Autowired
    private NavigationLinkRepository navigationLinkRepository;

    private final NavigationService navigationService;
    private final FooterService footerService;

    /**
     * Constructs a new LinkService with the provided NavigationService and FooterService dependencies.
     *
     * @param navigationService the NavigationService dependency
     * @param footerService the FooterService dependency
     */
    public LinkService(NavigationService navigationService, FooterService footerService) {
        this.navigationService = navigationService;
        this.footerService = footerService;
    }

    /**
     * Creates a link for a footer entity.
     *
     * @param id the ID of the footer
     * @param link the link to create
     * @return the created footer link
     * @throws ResponseStatusException if the associated footer is not found
     */
    public FooterLinks createFotLink(Long id, FooterLinks link){
        Optional<Footer> footer = footerService.getFooterById(id);
        if (footer.isPresent()) {
            link.setFooter(footer.get());
            return footerLinkRepository.save(link);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a link for a navigation entity.
     *
     * @param id the ID of the navigation
     * @param link the link to create
     * @return the created navigation link
     * @throws ResponseStatusException if the associated navigation is not found
     */
    public NavigationLinks createNavLink(Long id, NavigationLinks link){
        Optional<Navigation> nav = navigationService.getNavigationById(id);
        if (nav.isPresent()) {
            link.setNavigation(nav.get());
            return navigationLinkRepository.save(link);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes a link by its ID.
     *
     * @param id the ID of the link to delete
     * @param propClass the class representing the type of link (FooterLinks or NavigationLinks)
     */
    public <T> void deleteLink(Long id, Class<T> propClass){
        if (propClass == FooterLinks.class) {
            footerLinkRepository.deleteById(id);
        } else {
            navigationLinkRepository.deleteById(id);
        }
    }
}
