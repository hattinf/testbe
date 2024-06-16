package com.backend.flex.converter;

import com.backend.flex.model.components.props.showcase.Showcase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ShowcaseConverter implements AttributeConverter<Showcase, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Showcase showcase) {
        try {
            return objectMapper.writeValueAsString(showcase);
        } catch (JsonProcessingException jpe) {
            return null;
        }
    }

    @Override
    public Showcase convertToEntityAttribute(String value) {
        try {
            return objectMapper.readValue(value, Showcase.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
