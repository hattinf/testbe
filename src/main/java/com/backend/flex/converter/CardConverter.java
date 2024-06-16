package com.backend.flex.converter;

import com.backend.flex.model.components.props.cards.Cards;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CardConverter implements AttributeConverter<Cards, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Cards cards) {
        try {
            return objectMapper.writeValueAsString(cards);
        } catch (JsonProcessingException jpe) {
            return null;
        }
    }

    @Override
    public Cards convertToEntityAttribute(String value) {
        try {
            return objectMapper.readValue(value, Cards.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
