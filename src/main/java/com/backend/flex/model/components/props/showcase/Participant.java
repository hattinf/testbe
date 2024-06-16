package com.backend.flex.model.components.props.showcase;

public class Participant {
    private  String name;
    private  String description;
    private  String image;
    private  String link;

    public Participant() {
    }

    public Participant( String name,  String description,  String image,  String link) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.link = link;
    }

    public  String getName() {
        return name;
    }

    public void setName( String name) {
        this.name = name;
    }

    public  String getDescription() {
        return description;
    }

    public void setDescription( String description) {
        this.description = description;
    }

    public  String getImage() {
        return image;
    }

    public void setImage( String image) {
        this.image = image;
    }

    public  String getLink() {
        return link;
    }

    public void setLink( String link) {
        this.link = link;
    }
}
