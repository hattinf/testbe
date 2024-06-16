package com.backend.flex.model.components.props.bar;

import com.backend.flex.converter.BarConverter;
import com.backend.flex.model.components.props.Prop;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("BRP")
@Table(name = "bar_props")
public class BarProps implements Prop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "bar_color")
    private String barColor;

    @Convert(converter = BarConverter.class)
    @Column(name = "images", columnDefinition = "TEXT")
    private BarImages images;

    private BarProps() {}

    public BarProps(String barColor, BarImages images) {
        this.barColor = barColor;
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public String getBarColor() {
        return barColor;
    }

    public void setBarColor(String barColor) {
        this.barColor = barColor;
    }

    public BarImages getImages() {
        return images;
    }

    public void setImages(BarImages images) {
        this.images = images;
    }
}
