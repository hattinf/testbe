package com.backend.flex.controller;

import com.backend.flex.model.Page;
import com.backend.flex.model.Website;
import com.backend.flex.model.components.Components;
import com.backend.flex.model.components.ComponentsCreate;
import com.backend.flex.model.components.props.Prop;
import com.backend.flex.model.components.props.TextProps;
import com.backend.flex.model.security.User;
import com.backend.flex.security.services.UserDetailsImpl;
import com.backend.flex.service.ComponentService;
import com.backend.flex.service.PageService;
import com.backend.flex.service.WebsiteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PageControllerTest {

    @Mock
    private PageService pageService;

    @Mock
    private WebsiteService websiteService;

    @Mock
    private ComponentService componentService;

    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    private PageController pageController;

    private Page page;
    private UserDetailsImpl user;
    private Website website;

    @BeforeEach
    public void setup() {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        user = new UserDetailsImpl(
                1L,
                "username",
                "email",
                "password",
                authorities
        );
        User user_created = new User("username", "email", "password");
        user_created.setId(1L);
        page = new Page();
        page.setId(1L);
        website = new Website();
        website.setId(1L);
        website.setUser(user_created);
    }

    @Test
    public void testGetAllPagesByWebsite() {
        when(websiteService.getWebsiteById(1L)).thenReturn(Optional.of(website));
        when(pageService.getWebsitePages(website, "test")).thenReturn(List.of(page));
        ResponseEntity<List<Page>> response = pageController.getAllPagesByWebsite(1L, "test", user);
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetAllPagesByWebsiteNotFound() {
        when(websiteService.getWebsiteById(1L)).thenReturn(Optional.empty());
        ResponseEntity<List<Page>> response = pageController.getAllPagesByWebsite(1L, "test", user);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetPageById() {
        when(pageService.getPageById(1L)).thenReturn(Optional.of(page));
        ResponseEntity<Page> response = pageController.getPageById(1L, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetPageByIdNotFound() {
        when(pageService.getPageById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Page> response = pageController.getPageById(1L, user);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void createComponentForPage() {
        when(pageService.getPageById(1L)).thenReturn(Optional.of(page));
        when(componentService.createComponentProp("TEST", new Object(), mapper)).thenReturn(new TextProps());
        when(componentService.createComponents(new ComponentsCreate(), page, new TextProps())).thenReturn(new Components());
        ComponentsCreate newComponent = new ComponentsCreate();
        newComponent.setPage(1L);
        ResponseEntity<Components> response = pageController.creatNewPageComponent(newComponent, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void createComponentForPageNotFound() {
        when(pageService.getPageById(1L)).thenReturn(Optional.empty());
        ComponentsCreate newComponent = new ComponentsCreate();
        newComponent.setPage(1L);
        ResponseEntity<Components> response = pageController.creatNewPageComponent(newComponent, user);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void updatePage() {
        when(pageService.getPageById(1L)).thenReturn(Optional.of(page));
        ResponseEntity<Page> response = pageController.updatePage(1L, page, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updatePageNotFound() {
        when(pageService.getPageById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Page> response = pageController.updatePage(1L, page, user);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void hidePageToggle() {
        when(pageService.getPageById(1L)).thenReturn(Optional.of(page));
        when(pageService.togglePublishPageById(Optional.of(page))).thenAnswer(
                invocation -> {
                    page.setHidden(true);
                    return page;
                }
        );
        ResponseEntity<Page> response = pageController.hidePageToggle(1L, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, Objects.requireNonNull(response.getBody()).getHidden());
    }

    @Test
    public void hidePageToggleNotFound() {
        when(pageService.getPageById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Page> response = pageController.hidePageToggle(1L, user);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deletePageReturnsOk() {
        when(pageService.getPageById(1L)).thenReturn(Optional.of(page));
        ResponseEntity<HttpStatus> response = pageController.deletePage(1L, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deletePageReturnsNotFound() {
        when(pageService.getPageById(1L)).thenReturn(Optional.empty());
        ResponseEntity<HttpStatus> response = pageController.deletePage(1L, user);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
