package com.backend.flex.model.components.props.schedule;

import javax.xml.crypto.Data;
import java.util.Date;

public class Activity {
    private String title;
    private String description;
    private String from;
    private String to;
    private String location;

    public Activity() {
    }

    public Activity(String title, String description, String from, String to, String location) {
        this.title = title;
        this.description = description;
        this.from = from;
        this.to = to;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
