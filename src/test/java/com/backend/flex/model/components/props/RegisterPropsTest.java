package com.backend.flex.model.components.props;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RegisterPropsTest {

    private RegisterProps registerProps;

    @BeforeEach
    public void setUp() {
        registerProps = new RegisterProps();
    }

    @Test
    public void testSetGetId() {
        registerProps.setId(1L);
        assert(registerProps.getId() == 1L);
    }

    @Test
    public void testSetGetText() {
        registerProps.setText("Test");
        assert(registerProps.getText().equals("Test"));
    }

    @Test
    public void testSetGetSize() {
        registerProps.setTextSize("Test Size");
        assert registerProps.getTextSize().equals("Test Size");
    }

    @Test
    public void testSetGetWeight() {
        registerProps.setTextWeight("Test Weight");
        assert registerProps.getTextWeight().equals("Test Weight");
    }

    @Test
    public void testSetGetStyle() {
        registerProps.setTextStyle("Test Style");
        assert registerProps.getTextStyle().equals("Test Style");
    }

    @Test
    public void testSetGetSubText() {
        registerProps.setSubText("Test SubText");
        assert registerProps.getSubText().equals("Test SubText");
    }

    @Test
    public void testSetGetSubTextSize() {
        registerProps.setSubTextSize("Test SubTextSize");
        assert registerProps.getSubTextSize().equals("Test SubTextSize");
    }

    @Test
    public void testSetGetSubTextWeight() {
        registerProps.setSubTextWeight("Test SubTextWeight");
        assert registerProps.getSubTextWeight().equals("Test SubTextWeight");
    }

    @Test
    public void testSetGetSubTextStyle() {
        registerProps.setSubTextStyle("Test SubTextStyle");
        assert registerProps.getSubTextStyle().equals("Test SubTextStyle");
    }

    @Test
    public void testSetGetBackgroundColor() {
        registerProps.setBackgroundColor("Test BackgroundColor");
        assert registerProps.getBackgroundColor().equals("Test BackgroundColor");
    }

    @Test
    public void testSetGetColor() {
        registerProps.setColor("Test Color");
        assert registerProps.getColor().equals("Test Color");
    }

    @Test
    public void testConstructor() {
        RegisterProps registerProps = new RegisterProps("Test", "Test Size", "Test Weight", "Test Style", "Test SubText", "Test SubTextSize", "Test SubTextWeight", "Test SubTextStyle", "Test Color", "Test Color");
        assert registerProps.getText().equals("Test");
        assert registerProps.getTextSize().equals("Test Size");
        assert registerProps.getTextWeight().equals("Test Weight");
        assert registerProps.getTextStyle().equals("Test Style");
        assert registerProps.getSubText().equals("Test SubText");
        assert registerProps.getSubTextSize().equals("Test SubTextSize");
        assert registerProps.getSubTextWeight().equals("Test SubTextWeight");
        assert registerProps.getSubTextStyle().equals("Test SubTextStyle");
        assert registerProps.getBackgroundColor().equals("Test Color");
        assert registerProps.getColor().equals("Test Color");
    }
}
