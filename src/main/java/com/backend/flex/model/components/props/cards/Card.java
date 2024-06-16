package com.backend.flex.model.components.props.cards;

public class Card {
    private String title;
    private String titleSize;
    private String titleColor;
    private String description;
    private String descriptionSize;
    private String descriptionColor;
    private String image;
    private String color;

    public Card() {
    }

    public Card(String title, String titleSize, String titleColor, String description, String descriptionSize, String descriptionColor, String image, String color) {
        this.title = title;
        this.titleSize = titleSize;
        this.titleColor = titleColor;
        this.description = description;
        this.descriptionSize = descriptionSize;
        this.descriptionColor = descriptionColor;
        this.image = image;
        this.color = color;
    }

    public String getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(String titleSize) {
        this.titleSize = titleSize;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getDescriptionSize() {
        return descriptionSize;
    }

    public void setDescriptionSize(String descriptionSize) {
        this.descriptionSize = descriptionSize;
    }

    public String getDescriptionColor() {
        return descriptionColor;
    }

    public void setDescriptionColor(String descriptionColor) {
        this.descriptionColor = descriptionColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String string) {
        this.color = string;
    }
}
