package com.backend.flex.converter;

import com.backend.flex.model.components.props.faq.Faqs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FaqConverter implements AttributeConverter<Faqs, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Faqs faqs) {
        try {
            return objectMapper.writeValueAsString(faqs);
        } catch (JsonProcessingException jpe) {
            return null;
        }
    }

    @Override
    public Faqs convertToEntityAttribute(String value) {
        try {
            return objectMapper.readValue(value, Faqs.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
