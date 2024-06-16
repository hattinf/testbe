package com.backend.flex.model.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ComponentsCreateTest {

    private ComponentsCreate componentsCreate;

    @BeforeEach
    public void setUp() {
        componentsCreate = new ComponentsCreate();
    }

    @Test
    public void testGetSetType() {
        componentsCreate.setType("test");
        assert componentsCreate.getType().equals("test");
    }

    @Test
    public void testGetSetPage() {
        componentsCreate.setPage(1L);
        assert componentsCreate.getPage().equals(1L);
    }

    @Test
    public void testGetSetProps() {
        Object prop = new Object();
        componentsCreate.setProps(prop);
        assert componentsCreate.getProps().equals(prop);
    }

    @Test
    public void testGetSetName() {
        componentsCreate.setName("test");
        assert componentsCreate.getName().equals("test");
    }

    @Test
    public void testGetSetPosition() {
        componentsCreate.setPosition(1);
        assert componentsCreate.getPosition().equals(1);
    }

    @Test
    public void testConstructor() {
        ComponentsCreate componentsCreate = new ComponentsCreate(1L, new Object(), "test", "test", 1);
        assert componentsCreate.getPage().equals(1L);
        assert componentsCreate.getProps() != null;
        assert componentsCreate.getName().equals("test");
        assert componentsCreate.getType().equals("test");
        assert componentsCreate.getPosition().equals(1);
    }
}
