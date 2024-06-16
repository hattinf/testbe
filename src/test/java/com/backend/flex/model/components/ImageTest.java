package com.backend.flex.model.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ImageTest {
    private Image image;

    @BeforeEach
    public void setUp() {
        image = new Image("test");
    }

    @Test
    public void testGetSetId() {
        image.setId(1L);
        assert image.getId().equals(1L);
    }

}
