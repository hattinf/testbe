package com.backend.flex.service;

import com.backend.flex.model.Page;
import com.backend.flex.model.Website;
import com.backend.flex.model.footer.FooterLinks;
import com.backend.flex.model.navigation.Navigation;
import com.backend.flex.model.navigation.NavigationLinks;
import com.backend.flex.repository.FooterLinkRepository;
import com.backend.flex.repository.NavigationLinkRepository;
import com.backend.flex.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing page-related operations.
 * This class provides methods for CRUD operations on page entities.
 */
@Service
public class PageService {

    @Autowired
    PageRepository pageRepository;

    @Autowired
    NavigationLinkRepository navigationLinkRepository;

    @Autowired
    FooterLinkRepository footerLinkRepository;

    /**
     * Retrieves all pages.
     *
     * @return a list of all pages
     */
    public List<Page> getAllPages() {
        return pageRepository.findAll();
    }

    /**
     * Retrieves all pages by website.
     *
     * @param website the website to retrieve pages for
     * @param slug the slug of the page to retrieve
     * @return a list of pages for the specified website
     */
    public List<Page> getWebsitePages(Website website, String slug) {
        List<Page> pages =  website.getPage();
        if(slug != null){
            return pages.stream().filter(page -> page.getSlug().equals(slug)).toList();
        }
        return  pages;
    }

    /**
     * Retrieves a page by its ID.
     *
     * @param id the ID of the page to retrieve
     * @return an Optional containing the requested page, or empty if not found
     */
    public Optional<Page> getPageById(long id) {
        return pageRepository.findById(id);
    }

    /**
     * Updates an existing page by its ID.
     *
     * @param pageData the Optional containing the existing page data
     * @param updateData the updated page data
     * @return the updated page
     */
    public Page updatePageById(Optional<Page> pageData, Page updateData){
        Page _page = pageData.get();
        _page.setSlug(updateData.getSlug());
        _page.setTitle(updateData.getTitle());
        return pageRepository.save(_page);
    }

    /**
     * Publish an existing page by its ID.
     *
     * @param pageData the Optional containing the existing page data
     * @return the updated page
     */
    public Page togglePublishPageById(Optional<Page> pageData){
        Page _page = pageData.get();
        _page.setHidden(!_page.getHidden());
        return pageRepository.save(_page);
    }


    /**
     * Creates a new page.
     *
     * @param page the data of the page to create
     * @param pageId the ID of the parent page if this page is a subpage
     * @return the created page
     */
    public Page createPage(Page page, Optional<Long> pageId) {
        Page newPage = new Page(page.getSlug(), page.getTitle(), false, page.getWebsite());
        if (pageId.isPresent()) {
            Optional<Page> parentPage = pageRepository.findById(pageId.get());
            if (parentPage.isPresent()) {
                newPage.setSub(true);
                newPage.setParentPage(parentPage.get());
            }
        }

        Page _page = pageRepository.save(newPage);
        if(page.getWebsite().getNavigation() != null){
            if(page.getSub()){
                boolean loop = true;
                ArrayList<String> slugs = new ArrayList<>();
                Page current = _page;
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
                NavigationLinks link_ = new NavigationLinks(page.getTitle(), combinedSlug.toString(), page.getWebsite().getNavigation(), _page);
                navigationLinkRepository.save(link_);
                FooterLinks footerLink = new FooterLinks(page.getTitle(), combinedSlug.toString(), page.getWebsite().getFooter(), _page);
                footerLinkRepository.save(footerLink);

            } else {
                NavigationLinks link_ = new NavigationLinks(page.getTitle(), page.getSlug(), page.getWebsite().getNavigation(), _page);
                navigationLinkRepository.save(link_);
                FooterLinks footerLink = new FooterLinks(page.getTitle(), page.getSlug(), page.getWebsite().getFooter(), _page);
                footerLinkRepository.save(footerLink);
            }
        }
        return  _page;
    }

    /**
     * Deletes a page by its ID.
     *
     * @param id the ID of the page to delete
     */
    public void deletePageById(long id) {
        Optional<Page> p = this.getPageById(id);
        if(p.isPresent()){
            if (p.get().getSlug().equals("/")){
                throw new IllegalArgumentException("Cannot delete the home page");
            } else {
                pageRepository.delete(p.get());
            }
        }   else {
                throw new IllegalArgumentException("Page not found");
            }
        }
}
