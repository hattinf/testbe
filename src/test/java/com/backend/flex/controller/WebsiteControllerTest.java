package com.backend.flex.controller;

import com.backend.flex.model.Page;
import com.backend.flex.model.Website;
import com.backend.flex.model.WebsitePublicOutput;
import com.backend.flex.model.security.User;
import com.backend.flex.security.services.UserDetailsImpl;
import com.backend.flex.service.PageService;
import com.backend.flex.service.WebsiteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WebsiteControllerTest {

    @Mock
    private WebsiteService websiteService;

    @Mock
    private PageService pageService;

    @InjectMocks
    private WebsiteController websiteController;

    private Website website;
    private UserDetailsImpl user;

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
        website = new Website();
        website.setId(1L);
        website.setUser(user_created);
    }

    @Test
    public void getAllWebsitesReturnsAllWebsites() {
        when(websiteService.getAllWebsites()).thenReturn(Collections.singletonList(website));
        ResponseEntity<List<Website>> response = websiteController.getAllWebsites(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonList(website), response.getBody());
    }

    @Test
    public void getAllWebsitesReturnsAllWebsitesUser() {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        user = new UserDetailsImpl(
                1L,
                "username",
                "email",
                "password",
                authorities
        );
        when(websiteService.getAllWebsites()).thenReturn(Collections.singletonList(website));
        ResponseEntity<List<Website>> response = websiteController.getAllWebsites(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }

    @Test
    public void getWebsiteByIdReturnsWebsiteWhenPresent() {
        when(websiteService.getWebsiteById(1L)).thenReturn(Optional.of(website));
        ResponseEntity<Website> response = websiteController.getWebsiteById(1L, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(website, response.getBody());
    }

    @Test
    public void getWebsiteByIdReturnsNotFound() {
        when(websiteService.getWebsiteById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Website> response = websiteController.getWebsiteById(1L, user);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public  void getWebsiteByIdByPageReturnsPage(){

        when(websiteService.getWebsiteById(1L)).thenAnswer(
                invocation -> {
                    Website website = new Website();
                    website.setId(1L);
                    website.setPublished(true);
                    return Optional.of(website);
                }
        );
        when(websiteService.getWebsitePageByUrl("test", Collections.singletonList(new Page()))).thenReturn(Optional.of(new Page()));

        when(websiteService.getWebsitePageByUrl("test", Collections.singletonList(new Page()))).thenReturn(Optional.of(new Page())).thenAnswer(
                invocation -> {
                    Page page = new Page();
                    page.setSlug("test");
                    WebsitePublicOutput websitePublicOutput = new WebsitePublicOutput();
                    websitePublicOutput.setId(website.getId());
                    websitePublicOutput.setPage(page);
                    return  websitePublicOutput;
                }
        );
        ResponseEntity<WebsitePublicOutput> response = websiteController.getWebsiteByIdByPage(1L, "test", user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public  void getWebsiteByIdByPageReturnsNotFoundWhenNotPublished(){
        when(websiteService.getWebsiteById(1L)).thenAnswer(
                invocation -> {
                    Website website = new Website();
                    website.setId(1L);
                    website.setPublished(false);
                    return Optional.of(website);
                }
        );
        ResponseEntity<WebsitePublicOutput> response = websiteController.getWebsiteByIdByPage(1L, "test", user);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public  void getWebsiteByIdByPageReturnsNotFound(){
        when(websiteService.getWebsiteById(1L)).thenReturn(Optional.empty());
        ResponseEntity<WebsitePublicOutput> response = websiteController.getWebsiteByIdByPage(1L, "test", user);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void createNewPageReturnsCreatedPage() {
        when(websiteService.getWebsiteById(1L)).thenReturn(Optional.of(website));
        when(pageService.getWebsitePages(website, "test")).thenReturn(Collections.emptyList());
        when(pageService.createPage(new Page(), Optional.of(1L))).thenReturn(new Page());
        ResponseEntity<Page> response = websiteController.createPage(1L, Optional.of(1L), new Page(), user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void createNewPageReturnsWebsiteNotFound() {
        when(websiteService.getWebsiteById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Page> response = websiteController.createPage(1L, Optional.of(1L), new Page(), user);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void updateWebsiteReturnsUpdatedWebsite() {
        when(websiteService.getWebsiteById(1L)).thenReturn(Optional.of(website));
        when(websiteService.updateWebsiteById(Optional.of(website), website)).thenReturn(website);
        ResponseEntity<Website> response = websiteController.updateWebsite(1L, website, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateWebsiteReturnsNotFound() {
        when(websiteService.getWebsiteById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Website> response = websiteController.updateWebsite(1L, website, user);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void publishWebsiteReturnsOk() {
        when(websiteService.getWebsiteById(1L)).thenReturn(Optional.of(website));
        when(websiteService.publishWebsiteById(Optional.of(website))).thenAnswer(
                invocation -> {
                    website.setPublished(true);
                    return website;
                }
        );
        ResponseEntity<Website> response = websiteController.publishWebsiteToggle(1L, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, Objects.requireNonNull(response.getBody()).getPublished());
    }

    @Test
    public void publishWebsiteReturnsNotFound() {
        when(websiteService.getWebsiteById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Website> response = websiteController.publishWebsiteToggle(1L, user);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deleteWebsiteReturnsOk() {
        when(websiteService.getWebsiteById(1L)).thenReturn(Optional.of(website));
        ResponseEntity<HttpStatus> response = websiteController.deleteWebsite(1L, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deleteWebsiteReturnsNotFound() {
        when(websiteService.getWebsiteById(1L)).thenReturn(Optional.empty());
        ResponseEntity<HttpStatus> response = websiteController.deleteWebsite(1L, user);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
