package com.backend.flex.model.components.props.cards;

import java.util.List;

public class Cards {
    private List<Card> cards;

    public Cards() {
    }

    public Cards( List<Card> cards) {
        this.cards = cards;
    }

    public void setCards( List<Card> cards) {
        this.cards = cards;
    }

    public  List<Card> getCards() {
        return cards;
    }
}
