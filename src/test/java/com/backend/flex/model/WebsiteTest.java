package com.backend.flex.model;

import com.backend.flex.model.Website;
import com.backend.flex.model.components.Components;
import com.backend.flex.model.footer.Footer;
import com.backend.flex.model.navigation.Navigation;
import com.backend.flex.model.security.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class WebsiteTest {

    private Website website;

    @BeforeEach
    public void setUp() {
        website = new Website();
    }

    @Test
    public void websiteSetsAndGetsId() {
        long expectedId = 1L;
        website.setId(expectedId);
        assertEquals(expectedId, website.getId());
    }

    @Test
    public void websiteSetsAndGetsName() {
        String expectedName = "Test Name";
        website.setName(expectedName);
        assertEquals(expectedName, website.getName());
    }

    @Test
    public void websiteSetsAndGetsPublished() {
        website.setPublished(true);
        assertEquals(true, website.getPublished());
    }

    @Test
    public void websiteSetsAndGetsDescription() {
        String expectedDescription = "Test Description";
        website.setDescription(expectedDescription);
        assertEquals(expectedDescription, website.getDescription());
    }

    @Test
    public void websiteSetsAndGetsPage() {
        List<Page> expectedPage = Collections.emptyList();
        website.setPage(expectedPage);
        assertEquals(expectedPage, website.getPage());
    }

    @Test
    public void websiteSetsAndGetsFooter() {
        Footer expectedFooter = new Footer();
        website.setFooter(expectedFooter);
        assertEquals(expectedFooter, website.getFooter());
    }

    @Test
    public void websiteSetsAndGetsNavigation() {
        Navigation expectedNavigation = new Navigation();
        website.setNavigation(expectedNavigation);
        assertEquals(expectedNavigation, website.getNavigation());
    }

    @Test
    public void websiteSetsAndGetsComponents() {
        List<Components> expectedComponents = Collections.emptyList();
        website.setComponents(expectedComponents);
        assertEquals(expectedComponents, website.getComponents());
    }

    @Test
    public void websiteSetsAndGetsRegistered() {
        List<Register> expectedRegister = Collections.emptyList();
        website.setRegister(expectedRegister);
        assertEquals(expectedRegister, website.getRegister());
    }

    @Test
    public void websiteSetsAndGetsUser() {
        User expectedUser = new User();
        website.setUser(expectedUser);
        assertEquals(expectedUser, website.getUser());
    }

    @Test
    public void websiteToStringReturnsCorrectFormat() {
        String expectedToString = "Website{id=0, name='null', published=null, description='null', page=null, footer=null, navigation=null, components=null, register=null, user=null}";
        assertEquals(expectedToString, website.toString());
    }
}