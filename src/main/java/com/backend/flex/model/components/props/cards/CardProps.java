package com.backend.flex.model.components.props.cards;

import com.backend.flex.converter.CardConverter;
import com.backend.flex.model.components.props.Prop;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CRP")
@Table(name = "card_props")
public class CardProps implements Prop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Convert(converter = CardConverter.class)
    @Column(name = "data", columnDefinition = "TEXT")
    private Cards data;

    @Column(name = "background_color")
    private String backgroundColor;

    public Long getId() {
        return id;
    }

    public CardProps() {}

    public CardProps(Cards data, String backgroundColor) {
        this.data = data;
        this.backgroundColor = backgroundColor;
    }

    public Cards getData() {
        return data;
    }

    public void setData(Cards data) {
        this.data = data;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

}

