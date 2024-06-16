package com.backend.flex.model.components.props;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("IMG")
@Table(name = "image_props")
public class ImageProp implements Prop{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "image_link", columnDefinition = "TEXT")
    private String imageLink;

    @Column(name = "image_alt_text", columnDefinition = "TEXT")
    private String imageAltText;

    @Column(name = "image_background", columnDefinition = "TEXT")
    private String imageBackground;

    @Column(name = "image_height")
    private Integer imageHeight;

    @Column(name = "padding")
    private Integer padding;


    public ImageProp() {
    }

    public ImageProp(String imageLink, String imageAltText, String imageBackground) {
        this.imageLink = imageLink;
        this.imageAltText = imageAltText;
        this.imageBackground = imageBackground;
        this.imageHeight = 0;
        this.padding = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getImageAltText() {
        return imageAltText;
    }

    public void setImageAltText(String imageAltText) {
        this.imageAltText = imageAltText;
    }

    public String getImageBackground() {
        return imageBackground;
    }

    public void setImageBackground(String imageBackground) {
        this.imageBackground = imageBackground;
    }

    public Integer getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(Integer imageHeight) {
        this.imageHeight = imageHeight;
    }

    public Integer getPadding() {
        return padding;
    }

    public void setPadding(Integer padding) {
        this.padding = padding;
    }
}
