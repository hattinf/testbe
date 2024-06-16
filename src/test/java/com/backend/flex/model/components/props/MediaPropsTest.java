package com.backend.flex.model.components.props;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MediaPropsTest {

    private MediaProps mediaProps;

    @BeforeEach
    public void setUp() {
        mediaProps = new MediaProps();
    }

    @Test
    public void testMediaProps() {
        mediaProps = new MediaProps("media", "backgroundColor", true, "text", "textSize", "textWeight", "textStyle", "subText", "subTextSize", "subTextWeight", "subTextStyle", "color");
        assert(mediaProps.getMedia().equals("media"));
        assert(mediaProps.getBackgroundColor().equals("backgroundColor"));
        assert(mediaProps.getTextToggle().equals(true));
        assert(mediaProps.getText().equals("text"));
        assert(mediaProps.getTextSize().equals("textSize"));
        assert(mediaProps.getTextWeight().equals("textWeight"));
        assert(mediaProps.getTextStyle().equals("textStyle"));
        assert(mediaProps.getSubText().equals("subText"));
        assert(mediaProps.getSubTextSize().equals("subTextSize"));
        assert(mediaProps.getSubTextWeight().equals("subTextWeight"));
        assert(mediaProps.getSubTextStyle().equals("subTextStyle"));
        assert(mediaProps.getColor().equals("color"));
    }

    @Test
    public void testSetGetId(){
        mediaProps.setId(1L);
        assert(mediaProps.getId().equals(1L));
    }

    @Test
    public void testSetGetMedia(){
        mediaProps.setMedia("media");
        assert(mediaProps.getMedia().equals("media"));
    }

    @Test
    public void testSetGetBackgroundColor(){
        mediaProps.setBackgroundColor("backgroundColor");
        assert(mediaProps.getBackgroundColor().equals("backgroundColor"));
    }

    @Test
    public void testSetGetTextToggle(){
        mediaProps.setTextToggle(true);
        assert(mediaProps.getTextToggle().equals(true));
    }

    @Test
    public void testSetGetText(){
        mediaProps.setText("text");
        assert(mediaProps.getText().equals("text"));
    }

    @Test
    public void testSetGetTextSize(){
        mediaProps.setTextSize("textSize");
        assert(mediaProps.getTextSize().equals("textSize"));
    }

    @Test
    public void testSetGetTextWeight(){
        mediaProps.setTextWeight("textWeight");
        assert(mediaProps.getTextWeight().equals("textWeight"));
    }

    @Test
    public void testSetGetTextStyle(){
        mediaProps.setTextStyle("textStyle");
        assert(mediaProps.getTextStyle().equals("textStyle"));
    }

    @Test
    public void testSetGetSubText(){
        mediaProps.setSubText("subText");
        assert(mediaProps.getSubText().equals("subText"));
    }

    @Test
    public void testSetGetSubTextSize(){
        mediaProps.setSubTextSize("subTextSize");
        assert(mediaProps.getSubTextSize().equals("subTextSize"));
    }

    @Test
    public void testSetGetSubTextWeight(){
        mediaProps.setSubTextWeight("subTextWeight");
        assert(mediaProps.getSubTextWeight().equals("subTextWeight"));
    }

    @Test
    public void testSetGetSubTextStyle(){
        mediaProps.setSubTextStyle("subTextStyle");
        assert(mediaProps.getSubTextStyle().equals("subTextStyle"));
    }

    @Test
    public void testSetGetColor(){
        mediaProps.setColor("color");
        assert(mediaProps.getColor().equals("color"));
    }
}
