package com.backend.flex.converter;

import com.backend.flex.model.components.props.schedule.Activities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ScheduleConverter implements AttributeConverter<Activities, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Activities activities) {
        try {
            return objectMapper.writeValueAsString(activities);
        } catch (JsonProcessingException jpe) {
            return null;
        }
    }

    @Override
    public Activities convertToEntityAttribute(String value) {
        try {
            return objectMapper.readValue(value, Activities.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
