package com.backend.flex.model.navigation;

import com.backend.flex.model.Website;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NavigationCreateTest {

    private NavigationCreate navigationCreate;

    @BeforeEach
    public void setUp() {
        navigationCreate = new NavigationCreate("test", 1L);
    }

    @Test
    public void testGetLogo() {
        assertEquals("test", navigationCreate.getLogo());
    }

    @Test
    public void testGetWebsiteId() {
        assertEquals(1L, navigationCreate.getWebsite());
    }

    @Test
    public void testSetLogo() {
        NavigationCreate navigationCreate1 = new NavigationCreate();
        navigationCreate1.setLogo("test");
        assertEquals("test", navigationCreate.getLogo());
    }

}
