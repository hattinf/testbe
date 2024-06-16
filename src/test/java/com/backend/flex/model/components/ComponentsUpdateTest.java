package com.backend.flex.model.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ComponentsUpdateTest {

    private ComponentsUpdate componentsUpdate;

    @BeforeEach
    public void setUp() {
        componentsUpdate = new ComponentsUpdate();
    }

    @Test
    public void testGetSetType() {
        componentsUpdate.setType("test");
        assert componentsUpdate.getType().equals("test");
    }

    @Test
    public void testGetSetProp() {
        Object prop = new Object();
        componentsUpdate.setProps(prop);
        assert componentsUpdate.getProps().equals(prop);
    }

    @Test
    public void testGetSetName() {
        componentsUpdate.setName("test");
        assert componentsUpdate.getName().equals("test");
    }

    @Test
    public void testGetSetPosition() {
        componentsUpdate.setPosition(1);
        assert componentsUpdate.getPosition().equals(1);
    }
}
