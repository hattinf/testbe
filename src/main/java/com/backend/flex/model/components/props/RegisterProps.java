package com.backend.flex.model.components.props;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("RPR")
@Table(name = "register_props")
public class RegisterProps implements Prop{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text", columnDefinition = "TEXT")
    private String text;

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

    @Column(name = "color")
    private String color;

    @Column(name = "backgroundColor")
    private String backgroundColor;

    //need token here later to validate inputs data

    RegisterProps(){}

    public RegisterProps(String text, String textSize, String textWeight, String textStyle, String subText, String subTextSize, String subTextWeight, String subTextStyle, String color, String backgroundColor) {
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

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
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
}
