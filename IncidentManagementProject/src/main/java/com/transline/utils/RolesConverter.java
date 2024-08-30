//package com.transline.utils;
//
//import java.util.List;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import io.jsonwebtoken.lang.Arrays;
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//
//@Converter(autoApply = true)
//class RolesConverter implements AttributeConverter<List<String>, String> {
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public String convertToDatabaseColumn(List<String> roles) {
//        try {
//            return String.join(",", roles);
//        } catch (Exception e) {
//            throw new RuntimeException("Error converting list to string", e);
//        }
//    }
//
//    @Override
//    public List<String> convertToEntityAttribute(String dbData) {
//        if (dbData == null || dbData.isEmpty()) {
//            return List.of(); // or return an empty list
//        }
//        return Arrays.asList(dbData.split(","));
//    }
//}