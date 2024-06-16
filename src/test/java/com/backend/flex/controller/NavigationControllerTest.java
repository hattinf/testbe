package com.backend.flex.controller;

import com.backend.flex.model.Website;
import com.backend.flex.model.navigation.Navigation;
import com.backend.flex.model.navigation.NavigationCreate;
import com.backend.flex.model.navigation.NavigationUpdate;
import com.backend.flex.security.services.UserDetailsImpl;
import com.backend.flex.service.LinkService;
import com.backend.flex.service.NavigationService;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class NavigationControllerTest {

    @Mock
    private NavigationService navigationService;

    @Mock
    private WebsiteService websiteService;

    @Mock
    private LinkService linkService;

    @InjectMocks
    private NavigationController navigationController;

    private Navigation navigation;
    private Website website;
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
        navigation = new Navigation();
        navigation.setId(1L);
        website = new Website();
        website.setId(1L);
    }

    @Test
    public void getNavigationReturnsNavigationWhenPresent() {
        when(navigationService.getNavigationById(1L)).thenReturn(Optional.of(navigation));
        ResponseEntity<Navigation> response = navigationController.getNavigation(1L, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(navigation, response.getBody());
    }

    @Test
    public void createNavigationReturnsInternalServerErrorWhenExceptionThrown() {
        when(websiteService.getWebsiteById(1L)).thenReturn(Optional.of(website));
        when(navigationService.createNavigation(any(NavigationCreate.class))).thenThrow(new RuntimeException());
        ResponseEntity<Navigation> response = navigationController.createNavigation(new NavigationCreate(), user);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void updateNavigationReturnsUpdatedNavigationWhenSuccessful() {
        when(navigationService.getNavigationById(1L)).thenReturn(Optional.of(navigation));
        when(navigationService.updateNavigation(any(Navigation.class), any(NavigationUpdate.class))).thenReturn(navigation);
        ResponseEntity<Navigation> response = navigationController.updateNavigation(1L, new NavigationUpdate("test", "test", "test"), user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(navigation, response.getBody());
    }

    @Test
    public void updateNavigationReturnsNotFoundWhenNavigationNotPresent() {
        when(navigationService.getNavigationById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Navigation> response = navigationController.updateNavigation(1L, new NavigationUpdate("test", "test", "test"), user);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deleteNavigationReturnsOkWhenSuccessful() {
        when(navigationService.getNavigationById(1L)).thenReturn(Optional.of(navigation));
        doNothing().when(navigationService).deleteNavigation(1L);
        ResponseEntity<HttpStatus> response = navigationController.deleteNavigation(1L, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deleteNavigationReturnsInternalServerErrorWhenExceptionThrown() {
        when(navigationService.getNavigationById(1L)).thenReturn(Optional.of(navigation));
        doThrow(new RuntimeException()).when(navigationService).deleteNavigation(1L);
        ResponseEntity<HttpStatus> response = navigationController.deleteNavigation(1L, user);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
