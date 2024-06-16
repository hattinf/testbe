package com.backend.flex.controller;

import com.backend.flex.model.Website;
import com.backend.flex.model.components.Components;
import com.backend.flex.model.components.ComponentsUpdate;
import com.backend.flex.model.security.User;
import com.backend.flex.security.services.UserDetailsImpl;
import com.backend.flex.service.ComponentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest

class ComponentControllerTest {

    @Mock
    private ComponentService componentService;

    @InjectMocks
    private ComponentController componentController;

    private Components component;
    private ComponentsUpdate componentUpdate;
    private UserDetailsImpl user;

    @BeforeEach
    public void setup() {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        user = new UserDetailsImpl(
                1L,
                "username",
                "email",
                "password",
                authorities
        );
        User user_created = new User("username", "email", "password");
        user_created.setId(1L);
        Website test = new Website("test", user_created);
        component = new Components();
        component.setId(1L);
        component.setWebsite(test);
        componentUpdate = new ComponentsUpdate();
    }

    @Test
    public void getComponentReturnsAllComponents() {
        when(componentService.getAllComponents()).thenReturn(Collections.singletonList(component));
        ResponseEntity<List<Components>> response = componentController.getComponent();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    public void getComponentsByIDReturnsComponentWhenPresent() {
        when(componentService.getComponentById(1L)).thenReturn(Optional.of(component));

        ResponseEntity<Components> response = componentController.getComponentsByID(1L, user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(component, response.getBody());
    }

    @Test
    public void getComponentsByIDReturnsInternalServerErrorWhenComponentNotPresent() {
        when(componentService.getComponentById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Components> response = componentController.getComponentsByID(1L, user);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void updateComponentByIdReturnsUpdatedComponentWhenSuccessful() {
        when(componentService.getComponentById(1L)).thenReturn(Optional.of(component));
        when(componentService.updateComponent(component, componentUpdate, null)).thenReturn(component);
        ResponseEntity<Components> response = componentController.updateComponentById(1L, componentUpdate, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateComponentByIdReturnsNotFoundWhenComponentNotPresent() {
        when(componentService.getComponentById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Components> response = componentController.updateComponentById(1L, componentUpdate, user);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deleteComponentsReturnsNoContentWhenSuccessful() {
        when(componentService.getComponentById(1L)).thenReturn(Optional.of(component));
        ResponseEntity<HttpStatus> response = componentController.deleteComponents(1L, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deleteComponentsReturnsNotFoundWhenComponentNotPresent() {
        when(componentService.getComponentById(1L)).thenReturn(Optional.empty());
        ResponseEntity<HttpStatus> response = componentController.deleteComponents(1L, user);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}