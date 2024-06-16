package com.backend.flex.model.components.props;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HeroPropsTest {

    private HeroProps heroProps;

    @BeforeEach
    public void setUp() {
        heroProps = new HeroProps();
    }


    @Test
    public void testSetGetId(){
        heroProps.setId(1L);
        assert(heroProps.getId().equals(1L));
    }

    @Test
    public void testSetGetName(){
        heroProps.setName("name");
        assert(heroProps.getName().equals("name"));
    }

    @Test
    public void testSetGetSubText(){
        heroProps.setSubText("subText");
        assert(heroProps.getSubText().equals("subText"));
    }

    @Test
    public void testSetGetTextColor(){
        heroProps.setTextColor("textColor");
        assert(heroProps.getTextColor().equals("textColor"));
    }

    @Test
    public void testSetGetTextSize(){
        heroProps.setTextSize("textSize");
        assert(heroProps.getTextSize().equals("textSize"));
    }

    @Test
    public void testSetGetTextWeight(){
        heroProps.setTextWeight("textWeight");
        assert(heroProps.getTextWeight().equals("textWeight"));
    }

    @Test
    public void testSetGetTextStyle(){
        heroProps.setTextStyle("textStyle");
        assert(heroProps.getTextStyle().equals("textStyle"));
    }

    @Test
    public void testSetGetAlignText(){
        heroProps.setAlignText("alignText");
        assert(heroProps.getAlignText().equals("alignText"));
    }

    @Test
    public void testSetGetJustifyText(){
        heroProps.setJustifyText("justifyText");
        assert(heroProps.getJustifyText().equals("justifyText"));
    }

    @Test
    public void testSetGetSubTextSize(){
        heroProps.setSubTextSize("subText");
        assert(heroProps.getSubTextSize().equals("subText"));
    }

    @Test
    public void testSetGetSubTextWeight(){
        heroProps.setSubTextWeight("subTextWeight");
        assert(heroProps.getSubTextWeight().equals("subTextWeight"));
    }

    @Test
    public void testSetGetSubTextStyle(){
        heroProps.setSubTextStyle("subTextStyle");
        assert(heroProps.getSubTextStyle().equals("subTextStyle"));
    }

    @Test
    public void testSetGetBackgroundColor(){
        heroProps.setBackgroundColor("backgroundColor");
        assert(heroProps.getBackgroundColor().equals("backgroundColor"));
    }

    @Test
    public void testSetGetImage(){
        heroProps.setImage("image");
        assert(heroProps.getImage().equals("image"));
    }

    @Test
    public void testSetGetSizeY(){
        heroProps.setSizeY(10);
        assert heroProps.getSizeY().equals(10);
    }

    @Test
    public void testSetGetImageToggle(){
        heroProps.setImageToggle(true);
        assert heroProps.getImageToggle();
    }

    @Test
    public void testSetGetImageTintColor(){
        heroProps.setImageTintColor("imageTintColor");
        assert(heroProps.getImageTintColor().equals("imageTintColor"));
    }

    @Test
    public void testSetGetImageDarken(){
        heroProps.setImageDarken("imageDarken");
        assert(heroProps.getImageDarken().equals("imageDarken"));
    }

    @Test
    public void testConstructor(){
        HeroProps heroProps = new HeroProps("name", "alignText", "justifyText", "textSize", "textWeight", "textStyle", "subText", "subTextSize", "subTextWeight", "subTextStyle", "textColor",  "backgroundColor", 10, "image");
        assert(heroProps.getName().equals("name"));
        assert(heroProps.getAlignText().equals("alignText"));
        assert(heroProps.getJustifyText().equals("justifyText"));
        assert(heroProps.getTextSize().equals("textSize"));
        assert(heroProps.getTextWeight().equals("textWeight"));
        assert(heroProps.getTextStyle().equals("textStyle"));
        assert(heroProps.getSubText().equals("subText"));
        assert(heroProps.getSubTextSize().equals("subTextSize"));
        assert(heroProps.getSubTextWeight().equals("subTextWeight"));
        assert(heroProps.getSubTextStyle().equals("subTextStyle"));
        assert(heroProps.getTextColor().equals("textColor"));
        assert(heroProps.getBackgroundColor().equals("backgroundColor"));
        assert(heroProps.getSizeY().equals(10));
        assert(heroProps.getImage().equals("image"));
    }


}
