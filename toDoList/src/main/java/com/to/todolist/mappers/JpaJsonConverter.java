package com.to.todolist.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Converter
public class JpaJsonConverter implements AttributeConverter<Map<String, String>, String> {

    // ObjectMapper is thread safe
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, String> stringStringMap) {
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(stringStringMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String dbData) {
        Map<String, String> jsonMap = new HashMap<>();
        try {
            jsonMap = objectMapper.readValue(dbData, new TypeReference<Map<String, String>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonMap;
    }
}
