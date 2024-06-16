package com.backend.flex.model.navigation;

import com.backend.flex.model.Page;
import com.backend.flex.model.Website;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NavigationLinksTest {

    private NavigationLinks navigationLinks;

    @BeforeEach
    public void setUp() {
        Page page = new Page(
                "test",
                "test",
                false,
                new Website()
        );
        page.setId(1L);
        navigationLinks = new NavigationLinks("test", "test", new Navigation(), page);
    }

    @Test
    public void testGetSetId() {
        navigationLinks.setId(1L);
        assertEquals(1L, navigationLinks.getId());
    }

    @Test
    public void testGetSetName() {
        navigationLinks.setName("test");
        assertEquals("test", navigationLinks.getName());
    }

    @Test
    public void testGetSetUrl() {
        navigationLinks.setUrl("test");
        assertEquals("test", navigationLinks.getUrl());
    }

    @Test
    public void testGetSetNavigation() {
        Navigation navigation = new Navigation();
        navigationLinks.setNavigation(navigation);
        assertEquals(navigation, navigationLinks.getNavigation());
    }

    @Test
    public void testGetSetPage() {
        Page page = new Page(
                "test",
                "test",
                false,
                new Website()
        );
        page.setId(1L);
        navigationLinks.setPage(page);
        assertEquals(page, navigationLinks.getPage());
    }
}
