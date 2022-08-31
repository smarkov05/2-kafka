package com.mentoring.courier_service.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.serializer.support.SerializationFailedException;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonConvertorImpl implements JsonConvertor{

    private final ObjectMapper mapper;

    public String serializeToJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new SerializationFailedException("Can't serialize object. Object: %s.".formatted(obj));
        }
    }

    public <T> T deserializeJsonToClazz(String itemJson, Class<T> clazz) {
        try {
            return mapper.readValue(itemJson, clazz);
        } catch (JsonProcessingException e) {
            throw new SerializationFailedException("Can't serialize object. Object: %s.".formatted(itemJson));
        }
    }

    public <T> List<T> deserializeListItems(String itemsJson, Class<T> clazz) {
        try {

            return mapper.readValue(itemsJson, TypeFactory.defaultInstance().constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException | NullPointerException e) {
            throw new SerializationFailedException("Can't serialize list of objects. Objects: %s.".formatted(itemsJson));
        }
    }

}
