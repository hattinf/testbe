package com.backend.flex.model.components.props.schedule;

import java.util.List;

public class Activities {
    private String date;
    private String title;
    private String titleSize;
    private String titleStyle;
    private String titleWeight;
    private String titleColor;
    private String color;
    private String textColor;


    private List<Activity> activities;

    public Activities() {
    }

    public Activities(String date, String title, String titleSize, String titleStyle, String titleWeight, String titleColor, String color, String textColor, List<Activity> activities) {
        this.date = date;
        this.title = title;
        this.titleSize = titleSize;
        this.titleStyle = titleStyle;
        this.titleWeight = titleWeight;
        this.titleColor = titleColor;
        this.color = color;
        this.activities = activities;
        this.textColor = textColor;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(String titleSize) {
        this.titleSize = titleSize;
    }

    public String getTitleWeight() {
        return titleWeight;
    }

    public void setTitleWeight(String titleWeight) {
        this.titleWeight = titleWeight;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTitleStyle() {
        return titleStyle;
    }

    public void setTitleStyle(String titleStyle) {
        this.titleStyle = titleStyle;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

}
