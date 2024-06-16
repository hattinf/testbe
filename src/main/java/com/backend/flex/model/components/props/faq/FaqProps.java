package com.backend.flex.model.components.props.faq;

import com.backend.flex.converter.FaqConverter;
import com.backend.flex.model.components.props.Prop;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("FPR")
@Table(name = "faq_props")
public class FaqProps implements Prop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Convert(converter = FaqConverter.class)
    @Column(name = "data", columnDefinition = "TEXT")
    private Faqs data;

    @Column(name = "color")
    private String color;

    @Column(name = "text_color")
    private String textColor;

    @Column(name = "background_color")
    private String backgroundColor;

    @Column(name = "border_color")
    private String borderColor;


    public FaqProps() {}

    public FaqProps(Faqs data, String color, String textColor, String backgroundColor, String borderColor) {
        this.data = data;
        this.color = color;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }

    public Long getId() {
        return id;
    }

    public Faqs getData() {
        return data;
    }

    public void setData(Faqs data) {
        this.data = data;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }
}
