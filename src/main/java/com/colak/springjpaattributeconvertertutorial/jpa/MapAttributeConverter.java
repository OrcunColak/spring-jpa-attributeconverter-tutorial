package com.colak.springjpaattributeconvertertutorial.jpa;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Converter
@Component

@RequiredArgsConstructor
public class MapAttributeConverter implements AttributeConverter<Map<String, String>, String> {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(Map<String, String> attribute) {
        if (attribute == null) {
            attribute = new HashMap<>();
        }
        return objectMapper.writeValueAsString(attribute);
    }

    @SneakyThrows
    @Override
    public Map<String, String> convertToEntityAttribute(String dbData) {
        if (StringUtils.isBlank(dbData)) {
            return new HashMap<>();
        }
        return objectMapper.readValue(dbData, new TypeReference<HashMap<String, String>>() {});
    }
}
