package com.backend.flex.model.navigation;

import com.backend.flex.model.Page;
import com.backend.flex.model.Website;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NavigationTest {

    private Navigation navigation;

    @BeforeEach
    public void setUp() {
        navigation = new Navigation();
        Navigation navigation1 = new Navigation(
                "test",
                "test",
                "test",
                new Website()
        );
    }

    @Test
    public void testGetSetId() {
        navigation.setId(1L);
        assertEquals(1L, navigation.getId());
    }

    @Test
    public void testGetSetLogo() {
        navigation.setLogo("test");
        assertEquals("test", navigation.getLogo());
    }

    @Test
    public void testGetSetWebsite() {
        Website website = new Website();
        navigation.setWebsite(website);
        assertEquals(website, navigation.getWebsite());
    }

    @Test
    public void testGetSetBackgroundColor() {
        navigation.setBackgroundColor("test");
        assertEquals("test", navigation.getBackgroundColor());
    }

    @Test
    public void testGetSetLinkColor() {
        navigation.setLinkColor("test");
        assertEquals("test", navigation.getLinkColor());
    }

    @Test
    public void testGetSetLinks() {
        NavigationLinks navigationLinks = new NavigationLinks();
        Page newPage = new Page();
        newPage.setHidden(false);
        navigationLinks.setPage(newPage);
        navigation.setLinks(List.of(navigationLinks));
        assertEquals(List.of(navigationLinks), navigation.getLinks());
    }
}
