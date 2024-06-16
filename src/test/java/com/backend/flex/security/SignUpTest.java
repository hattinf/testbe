package com.backend.flex.security;

import com.backend.flex.security.requests.Signup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SignUpTest {

    private Signup signUp;

    @BeforeEach
    public void setUp() {
        signUp = new Signup();
        signUp.setEmail("test");
        signUp.setPassword("test");
        signUp.setUsername("test");
        signUp.setRole(Set.of("test"));
    }

    @Test
    public void testGetUsername() {
        assertEquals("test", signUp.getUsername());
    }

    @Test
    public void testSetUsername() {
        assertEquals("test", signUp.getUsername());
    }

    @Test
    public void testGetEmail() {
        assertEquals("test", signUp.getEmail());
    }

    @Test
    public void testSetEmail() {
        assertEquals("test", signUp.getEmail());
    }

    @Test
    public void testGetPassword() {
        assertEquals("test", signUp.getPassword());
    }

    @Test
    public void testSetPassword() {
        assertEquals("test", signUp.getPassword());
    }

    @Test
    public void testGetSetRoles() {
        assertEquals(Set.of("test"), signUp.getRole());
    }
}
