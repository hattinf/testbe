package com.backend.flex.controller;

import com.backend.flex.model.Register;
import com.backend.flex.model.Website;
import com.backend.flex.model.components.Components;
import com.backend.flex.security.services.UserDetailsImpl;
import com.backend.flex.service.RegisterService;
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
public class RegisterControllerTest {

    @Mock
    private RegisterService registerService;

    @Mock
    private WebsiteService websiteService;

    @InjectMocks
    private RegisterController registerController;

    private Register register;
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
        register = new Register();
        register.setId(1L);
        website = new Website();
        website.setId(1L);
    }

    @Test
    public void getAllPagesByWebsiteReturnsAll() {
        when(websiteService.getWebsiteById(1L)).thenReturn(Optional.of(website));
        when(registerService.getRegisterByWebsiteId(1L)).thenReturn(Collections.singletonList(register));
        ResponseEntity<List<Register>> response = registerController.getAllPagesByWebsite(1L, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    public void createRegisterReturnsCreatedRegister() {
        when(websiteService.getWebsiteById(1L)).thenAnswer(
                invocation -> {
                    Components components = new Components();
                    components.setType("RPR");
                    website.setComponents(List.of(components));
                    return Optional.of(website);
                }
        );
        when(registerService.createRegister(register)).thenReturn(register);
        ResponseEntity<Register> response = registerController.createRegister(register, 1L);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(register, response.getBody());
    }

    @Test
    public void createRegisterReturnsInternalError() {
        when(websiteService.getWebsiteById(1L)).thenAnswer(
                invocation -> {
                    Components components = new Components();
                    components.setType("TPR");
                    website.setComponents(List.of(components));
                    return Optional.of(website);
                }
        );
        ResponseEntity<Register> response = registerController.createRegister(register, 1L);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void createRegisterReturnsNotFound() {
        when(websiteService.getWebsiteById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Register> response = registerController.createRegister(register, 1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deleteRegisterTest() {
        ResponseEntity<HttpStatus> response = registerController.deleteRegister(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}