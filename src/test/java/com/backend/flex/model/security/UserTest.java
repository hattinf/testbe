package com.backend.flex.model.security;

import com.backend.flex.model.Website;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("username", "email", "password");
    }

    @Test
    public void testGetUsername() {
        assertEquals("username", user.getUsername());
    }

    @Test
    public void testSetUsername() {
        user.setUsername("newUsername");
        assertEquals("newUsername", user.getUsername());
    }

    @Test
    public void testGetEmail() {
        assertEquals("email", user.getEmail());
    }

    @Test
    public void testSetEmail() {
        user.setEmail("newEmail");
        assertEquals("newEmail", user.getEmail());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    public void testGetSetRoles() {
        Role role = new Role(ERole.ROLE_ADMIN);
        user.setRoles(Set.of(role));
        assertEquals(Set.of(role), user.getRoles());
    }

    @Test
    public void testGetSetWebsiteList() {
        Website website = new Website("Test website", user);
        user.setWebsiteList(List.of(website));
        assertEquals(List.of(website), user.getWebsiteList());
    }

    @Test
    public void testGetId() {
        user.setId(1L);
        assertEquals(1L, user.getId());
    }

}
