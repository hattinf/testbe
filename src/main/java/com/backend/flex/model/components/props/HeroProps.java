package com.backend.flex.model.components.props;

import jakarta.persistence.*;

/**
 * Represents a HeroProps entity which implements the Prop interface.
 * This class is mapped to the "hero_props" table in the database.
 */
@Entity
@DiscriminatorValue("HRP")
@Table(name = "hero_props")
public class HeroProps implements Prop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", columnDefinition = "TEXT")
    private String name;

    @Column(name = "text_size", columnDefinition = "TEXT")
    private String textSize;

    @Column(name = "text_weight")
    private String textWeight;

    @Column(name = "text_style")
    private String textStyle;

    @Column(name = "sub_text")
    private String subText;

    @Column(name = "sub_text_size")
    private String subTextSize;

    @Column(name = "sub_text_weight")
    private String subTextWeight;

    @Column(name = "sub_text_style")
    private String subTextStyle;

    @Column(name = "textColor")
    private String textColor;

    @Column(name = "backgroundColor")
    private String backgroundColor;

    @Column(name = "sizeY")
    private Integer sizeY;

    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    @Column(name = "image_tint")
    private String imageTintColor;

    @Column(name = "image_darken")
    private String imageDarken;

    @Column(name = "image_toggle")
    private Boolean imageToggle;

    @Column(name = "align_text")
    private String alignText;

    @Column(name = "justify_text")
    private String justifyText;


    HeroProps(){}

    public HeroProps(String name, String alignText, String justifyText, String textSize, String textWeight, String textStyle, String subText, String subTextSize, String subTextWeight, String subTextStyle, String textColor, String backgroundColor, Integer sizeY, String image) {
        this.name = name;
        this.textSize = textSize;
        this.textWeight = textWeight;
        this.textStyle = textStyle;
        this.subText = subText;
        this.subTextSize = subTextSize;
        this.subTextWeight = subTextWeight;
        this.subTextStyle = subTextStyle;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.sizeY = sizeY;
        this.image = image;
        this.imageToggle = false;
        this.alignText = alignText;
        this.justifyText = justifyText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getTextSize() {
        return textSize;
    }

    public void setTextSize(String textSize) {
        this.textSize = textSize;
    }

    public String getTextWeight() {
        return textWeight;
    }

    public void setTextWeight(String textWeight) {
        this.textWeight = textWeight;
    }

    public String getTextStyle() {
        return textStyle;
    }

    public void setTextStyle(String textStyle) {
        this.textStyle = textStyle;
    }

    public String getSubTextSize() {
        return subTextSize;
    }

    public void setSubTextSize(String subTextSize) {
        this.subTextSize = subTextSize;
    }

    public String getSubTextWeight() {
        return subTextWeight;
    }

    public void setSubTextWeight(String subTextWeight) {
        this.subTextWeight = subTextWeight;
    }

    public String getSubTextStyle() {
        return subTextStyle;
    }

    public void setSubTextStyle(String subTextStyle) {
        this.subTextStyle = subTextStyle;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Integer getSizeY() {
        return sizeY;
    }

    public void setSizeY(Integer sizeY) {
        this.sizeY = sizeY;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageTintColor() {
        return imageTintColor;
    }

    public void setImageTintColor(String imageTintColor) {
        this.imageTintColor = imageTintColor;
    }

    public String getImageDarken() {
        return imageDarken;
    }

    public void setImageDarken(String imageDarken) {
        this.imageDarken = imageDarken;
    }

    public boolean getImageToggle() {
        return imageToggle;
    }

    public void setImageToggle(boolean imageToggle) {
        this.imageToggle = imageToggle;
    }

    public String getAlignText() {
        return alignText;
    }

    public void setAlignText(String alignText) {
        this.alignText = alignText;
    }

    public String getJustifyText() {
        return justifyText;
    }

    public void setJustifyText(String justifyText) {
        this.justifyText = justifyText;
    }

}

