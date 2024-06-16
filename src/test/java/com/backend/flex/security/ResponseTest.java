package com.backend.flex.security;

import com.backend.flex.security.responses.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ResponseTest {

    private Response response;

    @BeforeEach
    public void setUp() {
        response = new Response(
                "test",
                1L,
                "user",
                "email",
                List.of("test")
        );
    }

    @Test
    public void testGetAccessToken() {
        String accessToken = "New Token";
        response.setAccessToken(accessToken);
        assert response.getAccessToken().equals(accessToken);
    }

    @Test
    public void testGetTokenType() {
        String tokenType = "New Token Type";
        response.setTokenType(tokenType);
        assert response.getTokenType().equals(tokenType);
    }

    @Test
    public void testGetId() {
        Long id = 2L;
        response.setId(id);
        assert response.getId().equals(id);
    }

    @Test
    public void testGetEmail() {
        String email = "New Email";
        response.setEmail(email);
        assert response.getEmail().equals(email);
    }

    @Test
    public void testGetUsername() {
        String username = "New Username";
        response.setUsername(username);
        assert response.getUsername().equals(username);
    }

    @Test
    public void testResponse() {
        assert response.getRoles().equals(List.of("test"));
    }

}
