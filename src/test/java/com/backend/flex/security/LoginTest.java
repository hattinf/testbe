package com.backend.flex.security;

import com.backend.flex.security.requests.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LoginTest {

    private Login login;

    @BeforeEach
    public void setUp() {
        login = new Login();
        login.setUsername("test");
        login.setPassword("test");
    }

    @Test
    public void testGetUsername() {
        assertEquals("test", login.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("test", login.getPassword());
    }
}
