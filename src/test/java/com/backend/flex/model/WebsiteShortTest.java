package com.backend.flex.model;

import com.backend.flex.model.website.WebsiteShort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WebsiteShortTest {

    private WebsiteShort websiteShort;

    @BeforeEach
    public void setUp() {
        websiteShort = new WebsiteShort();
        WebsiteShort websiteShortNew = new WebsiteShort(1L, "Test Name", "Test Description");
    }

    @Test
    public void testSetGetName() {
        String expectedName = "Test Name";
        websiteShort.setName(expectedName);
        assertEquals(expectedName, websiteShort.getName());
    }

    @Test
    public void testSetGetDescription() {
        String expectedDescription = "Test Description";
        websiteShort.setDescription(expectedDescription);
        assertEquals(expectedDescription, websiteShort.getDescription());
    }

    @Test
    public void testSetGetId() {
        long expectedId = 1L;
        websiteShort.setId(expectedId);
        assertEquals(expectedId, websiteShort.getId());
    }

}
