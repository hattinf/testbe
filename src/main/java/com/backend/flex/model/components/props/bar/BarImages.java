package com.backend.flex.model.components.props.bar;

import java.util.List;

public class BarImages {
    private List<BarImage> images;
    public BarImages() {}

    public BarImages(List<BarImage> images) {
        this.images = images;
    }

    public List<BarImage> getImages() {
        return images;
    }

    public void setImages(List<BarImage> images) {
        this.images = images;
    }
}
