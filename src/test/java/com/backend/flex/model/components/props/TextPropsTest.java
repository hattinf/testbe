package com.backend.flex.model.components.props;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TextPropsTest {

    private TextProps textProps;

    @BeforeEach
    public void setUp() {
        textProps = new TextProps();
    }

    @Test
    public void testSetGetText() {
        textProps.setText("Test");
        assert(textProps.getText().equals("Test"));
    }

    @Test
    public void testSetGetSize() {
        textProps.setTextSize("Test Size");
        assert textProps.getTextSize().equals("Test Size");
    }

    @Test
    public void testSetGetWeight() {
        textProps.setTextWeight("Test Weight");
        assert textProps.getTextWeight().equals("Test Weight");
    }

    @Test
    public void testSetGetStyle() {
        textProps.setTextStyle("Test Style");
        assert textProps.getTextStyle().equals("Test Style");
    }

    @Test
    public void testSetGetSubText() {
        textProps.setSubText("Test SubText");
        assert textProps.getSubText().equals("Test SubText");
    }

    @Test
    public void testSetGetSubTextSize() {
        textProps.setSubTextSize("Test SubTextSize");
        assert textProps.getSubTextSize().equals("Test SubTextSize");
    }

    @Test
    public void testSetGetSubTextWeight() {
        textProps.setSubTextWeight("Test SubTextWeight");
        assert textProps.getSubTextWeight().equals("Test SubTextWeight");
    }

    @Test
    public void testSetGetSubTextStyle() {
        textProps.setSubTextStyle("Test SubTextStyle");
        assert textProps.getSubTextStyle().equals("Test SubTextStyle");
    }

    @Test
    public void testSetGetColor() {
        textProps.setColor("Test Color");
        assert textProps.getColor().equals("Test Color");
    }

    @Test
    public void testSetGetBackgroundColor() {
        textProps.setBackgroundColor("Test Background Color");
        assert textProps.getBackgroundColor().equals("Test Background Color");
    }

    @Test
    public void testSetGetId() {
        textProps.setId(1L);
        assert textProps.getId().equals(1L);
    }

    @Test
    public void testSetGetToggleSide() {
        textProps.setToggleSide(true);
        assert textProps.getToggleSide().equals(true);
    }

    @Test
    public void testSetGetToggleColor() {
        textProps.setToggleColor("Test Toggle Color");
        assert textProps.getToggleColor().equals("Test Toggle Color");
    }

    @Test
    public void testSetGetToggleImage() {
        textProps.setImageToggle(true);
        assert textProps.getImageToggle().equals(true);
    }

    @Test
    public void testSetGetImage() {
        textProps.setImage("Test Image");
        assert textProps.getImage().equals("Test Image");
    }

    @Test
    public void testSetGetToggleFlip() {
        textProps.setToggleFlip(true);
        assert textProps.getToggleFlip().equals(true);
    }

    @Test
    public void testSetGetAlign() {
        textProps.setAlignText("Test Align");
        assert textProps.getAlignText().equals("Test Align");
    }

    @Test
    public void testSetGetJustify() {
        textProps.setJustifyText("Test Justify");
        assert textProps.getJustifyText().equals("Test Justify");
    }

    @Test
    public void testSetGetImageTintColor() {
        textProps.setImageTintColor("Test ImageTintColor");
        assert textProps.getImageTintColor().equals("Test ImageTintColor");
    }

    @Test
    public void testSetGetImageDarken() {
        textProps.setImageDarken("Test ImageDarken");
        assert textProps.getImageDarken().equals("Test ImageDarken");
    }

    @Test
    public void testConstructor(){
        TextProps textProps = new TextProps(
                "test",
                "test",
                "test",
                "test",
                "test",
                "test",
                "test",
                "test",
                "test",
                "test",
                "test",
                "test",
                true,
                "test",
                true,
                "test"
        );
        assert textProps.getText().equals("test");
        assert textProps.getTextSize().equals("test");
        assert textProps.getTextWeight().equals("test");
        assert textProps.getTextStyle().equals("test");
        assert textProps.getSubText().equals("test");
    }

}
