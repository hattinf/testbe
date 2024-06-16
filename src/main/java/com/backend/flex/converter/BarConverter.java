package com.backend.flex.converter;

import com.backend.flex.model.components.props.bar.BarImages;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BarConverter implements AttributeConverter<BarImages, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(BarImages barProps) {
        try {
            return objectMapper.writeValueAsString(barProps);
        } catch (JsonProcessingException jpe) {
            return null;
        }
    }

    @Override
    public BarImages convertToEntityAttribute(String value) {
        try {
            return objectMapper.readValue(value, BarImages.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
