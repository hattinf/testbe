package com.backend.flex.model.components;

import com.backend.flex.model.Page;
import com.backend.flex.model.Website;
import com.backend.flex.model.components.props.Prop;
import com.backend.flex.model.components.props.TextProps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ComponentsTest {

    private Components components;

    @BeforeEach
    public void setUp() {
        components = new Components();
    }

    @Test
    public void testGetSetType() {
        components.setType("test");
        assert components.getType().equals("test");
    }

    @Test
    public void testGetSetPage() {
        Page page = new Page();
        components.setPage(page);
        assert components.getPage().equals(page);
    }

    @Test
    public void testGetSetProp() {
        TextProps prop = new TextProps();
        components.setProp(prop);
        assert components.getProp().equals(prop);
    }

    @Test
    public void testGetSetName() {
        components.setName("test");
        assert components.getName().equals("test");
    }

    @Test
    public void testGetSetPosition() {
        components.setPosition(1);
        assert components.getPosition().equals(1);
    }

    @Test
    public void testGetSetId() {
        components.setId(1L);
        assert components.getId().equals(1L);
    }

    @Test
    public void testConstructor() {
        Page page = new Page();
        TextProps prop = new TextProps();
        Website website = new Website();
        Components components = new Components("test", page, prop, "test", website, 1);
        assert components.getPage().equals(page);
        assert components.getProp().equals(prop);
        assert components.getName().equals("test");
        assert components.getType().equals("test");
        assert components.getPosition().equals(1);
    }

}
