package com.backend.flex.model.components.props;

import jakarta.persistence.*;

/**
 * Represents a TextProp entity which implements the Prop interface.
 * This class is mapped to the "text_props" table in the database.
 */
@Entity
@DiscriminatorValue("TPR")
@Table(name = "text_props")
public class TextProps implements Prop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text", columnDefinition = "TEXT")
    private String text;

    @Column(name = "text_size")
    private String textSize;

    @Column(name = "text_weight")
    private String textWeight;

    @Column(name = "text_style")
    private String textStyle;

    @Column(name = "sub_text", columnDefinition = "TEXT")
    private String subText;

    @Column(name = "sub_text_size")
    private String subTextSize;

    @Column(name = "sub_text_weight")
    private String subTextWeight;

    @Column(name = "sub_text_style")
    private String subTextStyle;

    @Column(name = "color")
    private String color;

    @Column(name = "background_color")
    private String backgroundColor;

    @Column(name = "toggle_side")
    private Boolean toggleSide;

    @Column(name = "toggle_color")
    private String toggleColor;

    @Column(name = "toggle_flip")
    private Boolean toggleFlip;

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

    public TextProps(){}

    public TextProps(String text, String alignText, String justifyText, String textSize, String textWeight, String textStyle, String subText, String subTextSize, String subTextWeight, String subTextStyle, String color, String backgroundColor, Boolean toggleSide, String toggleColor, Boolean toggleFlip, String image) {
        this.text = text;
        this.textSize = textSize;
        this.textWeight = textWeight;
        this.textStyle = textStyle;
        this.subText = subText;
        this.subTextSize = subTextSize;
        this.subTextWeight = subTextWeight;
        this.subTextStyle = subTextStyle;
        this.color = color;
        this.backgroundColor = backgroundColor;
        this.toggleSide = toggleSide;
        this.toggleColor = toggleColor;
        this.toggleFlip = toggleFlip;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
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

    public Boolean getToggleSide() {
        return toggleSide;
    }

    public void setToggleSide(Boolean toggleSide) {
        this.toggleSide = toggleSide;
    }

    public String getToggleColor() {
        return toggleColor;
    }

    public void setToggleColor(String toggleColor) {
        this.toggleColor = toggleColor;
    }

    public Boolean getToggleFlip() {
        return toggleFlip;
    }

    public void setToggleFlip(Boolean toggleFlip) {
        this.toggleFlip = toggleFlip;
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

    public Boolean getImageToggle() {
        return imageToggle;
    }

    public void setImageToggle(Boolean imageToggle) {
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
