package com.backend.flex.model.components.props;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("MDA")
@Table(name = "MediaProps")
public class MediaProps implements Prop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "media", columnDefinition = "TEXT")
    private String media;

    @Column(name = "background_color")
    private String backgroundColor;

    @Column(name = "text_toggle")
    private Boolean textToggle;

    @Column(name = "text")
    private String text;

    @Column(name = "text_size")
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

    @Column(name = "color")
    private String color;

    public MediaProps() {}

    public MediaProps(String media, String backgroundColor, Boolean textToggle, String text, String textSize, String textWeight, String textStyle, String subText, String subTextSize, String subTextWeight, String subTextStyle, String color) {
        this.media = media;
        this.backgroundColor = backgroundColor;
        this.textToggle = textToggle;
        this.text = text;
        this.textSize = textSize;
        this.textWeight = textWeight;
        this.textStyle = textStyle;
        this.subText = subText;
        this.subTextSize = subTextSize;
        this.subTextWeight = subTextWeight;
        this.subTextStyle = subTextStyle;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Boolean getTextToggle() {
        return textToggle;
    }

    public void setTextToggle(Boolean textToggle) {
        this.textToggle = textToggle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
