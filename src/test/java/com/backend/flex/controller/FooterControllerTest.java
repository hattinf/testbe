package com.backend.flex.controller;

import com.backend.flex.model.Website;
import com.backend.flex.model.footer.Footer;
import com.backend.flex.model.footer.FooterCreate;
import com.backend.flex.model.footer.FooterUpdate;
import com.backend.flex.model.security.User;
import com.backend.flex.security.services.UserDetailsImpl;
import com.backend.flex.service.FooterService;
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

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FooterControllerTest {

    @Mock
    private WebsiteService websiteService;

    @Mock
    private FooterService footerService;

    @InjectMocks
    private FooterController footerController;

    private Footer footer;
    private UserDetailsImpl user;

    @BeforeEach
    public void setUp() {
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
        footer = new Footer();
        footer.setId(1L);
    }

    @Test
    public void getFooterReturnsFooter() {
        when(footerService.getFooterById(1L)).thenReturn(Optional.of(footer));
        ResponseEntity<Footer> response = footerController.getFooter(1L, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(footer, response.getBody());
    }

    @Test
    public void createFooterReturnsFooter() {
        FooterCreate footerCreate = new FooterCreate();
        Website website = new Website();
        website.setId(1L);
        footerCreate.setWebsite(1L);
        when(websiteService.getWebsiteById(1L)).thenReturn(Optional.of(website));
        when(footerService.createFooter(footerCreate)).thenReturn(footer);
        ResponseEntity<Footer> response = footerController.createFooter(footerCreate, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateFooterReturnsFooter() {
        FooterUpdate footerUpdate = new FooterUpdate();
        footerUpdate.setBottomText("bottomText");
        footerUpdate.setBackgroundColor("backgroundColor");
        footerUpdate.setLinkColor("linkColor");
        Website website = new Website();
        website.setId(1L);
        footer.setWebsite(website);
        when(footerService.getFooterById(1L)).thenReturn(Optional.of(footer));
        when(footerService.updateFooter(footer, footerUpdate)).thenReturn(footer);
        ResponseEntity<Footer> response = footerController.updateFooter(1L, footerUpdate, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateFooterReturnsFooterNOtFound() {
        Website website = new Website();
        website.setId(1L);
        footer.setWebsite(website);
        when(footerService.getFooterById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Footer> response = footerController.updateFooter(1L, new FooterUpdate(), user);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deleteFooterReturnsNoContent() {
        when(footerService.getFooterById(1L)).thenReturn(Optional.of(footer));
        ResponseEntity<HttpStatus> response = footerController.deleteFooter(1L, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}