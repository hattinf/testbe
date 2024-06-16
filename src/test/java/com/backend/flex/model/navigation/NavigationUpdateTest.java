package com.backend.flex.model.navigation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NavigationUpdateTest {

    private NavigationUpdate navigationUpdate;

    @BeforeEach
    public void setUp() {
        navigationUpdate = new NavigationUpdate("test", "test", "test");
    }

    @Test
    public void testGetLogo() {
        assertEquals("test", navigationUpdate.getLogo());
    }

    @Test
    public void testGetBackgroundColor() {
        assertEquals("test", navigationUpdate.getBackgroundColor());
    }

    @Test
    public void testGetLinkColor() {
        assertEquals("test", navigationUpdate.getLinkColor());
    }
}
