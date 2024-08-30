//package com.transline;
//
//import java.io.IOException;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.transline.entities.Role;
//import com.transline.entities.User;
//
//public class CustomUserSerializer extends JsonSerializer<User> {
//    @Override
//    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
//        jsonGenerator.writeStartObject();
//        jsonGenerator.writeStringField("userId", user.getUserId());
//        jsonGenerator.writeStringField("userName", user.getUsername());
//        jsonGenerator.writeStringField("status", user.getStatus());
//        jsonGenerator.writeStringField("cmpCd", user.getCompanyMst().getCmpCd());
//        jsonGenerator.writeFieldName("roles");
//        jsonGenerator.writeStartArray();
//        for (Role role : user.getRoles()) {
//            jsonGenerator.writeObject(role);
//        }
//        jsonGenerator.writeEndArray();
//        jsonGenerator.writeEndObject();
//    }
//}
