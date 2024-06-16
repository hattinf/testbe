package com.backend.flex.model;

import com.backend.flex.model.navigation.NavigationLinks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PageTest {

    private Page page;
    private Website website;
    private Page fullPage;

    @BeforeEach
    public void setUp() {
        website = new Website();
        website.setId(1L);
        page = new Page();
        fullPage = new Page(
               "test",
                "test",
                true,
                website
        );
    }

//    @Test
//    public void pageCreateWorks() {
//        Page expectedPage = new Page(
//                "test",
//                "test",
//                true,
//                website
//        );
//        assertEquals(expectedPage, fullPage);
//    }

    @Test
    public void pageSetsAndGetsId() {
        long expectedId = 1L;
        page.setId(expectedId);
        assertEquals(expectedId, page.getId());
    }

    @Test
    public void pageSetsAndGetsSlug() {
        String expectedSlug = "Test Slug";
        page.setSlug(expectedSlug);
        assertEquals(expectedSlug, page.getSlug());
    }

    @Test
    public void pageSetsAndGetsWebsite() {
        Website expectedWebsite = new Website();
        page.setWebsite(expectedWebsite);
        assertEquals(expectedWebsite, page.getWebsite());
    }

    @Test
    public void pageSetsAndGetsTitle() {
        String expectedTitle = "Test Title";
        page.setTitle(expectedTitle);
        assertEquals(expectedTitle, page.getTitle());
    }

    @Test
    public void pageSetsAndGetsSub() {
        Boolean expectedSub = true;
        page.setSub(expectedSub);
        assertEquals(expectedSub, page.getSub());
    }

    @Test
    public void pageSetsAndGetsParentPage() {
        Page expectedParentPage = new Page();
        page.setParentPage(expectedParentPage);
        assertEquals(expectedParentPage, page.getParentPage());
    }

    @Test
    public void pageSetsAndGetsPage() {
        List<Page> expectedPage = Collections.emptyList();
        page.setPage(expectedPage);
        assertEquals(expectedPage, page.getPage());
    }

    @Test
    public void pageSetsAndGetsHidden() {
        Boolean expectedHidden = true;
        page.setHidden(expectedHidden);
        assertEquals(expectedHidden, page.getHidden());
    }

    @Test
    public void pageInit() {
        Page page = new Page(
                "test",
                "test",
                true,
                website
        );
        assertEquals("test", page.getSlug());
        assertEquals("test", page.getTitle());
        assertEquals(true, page.getSub());
        assertEquals(website, page.getWebsite());
    }

    @Test
    public void pageSetsAndGetsNavigsationLink(){
        NavigationLinks expectedNavigationLink = new NavigationLinks();
        page.setNavigationLink(expectedNavigationLink);
        assertEquals(expectedNavigationLink, page.getNavigationLink());
    }
}
