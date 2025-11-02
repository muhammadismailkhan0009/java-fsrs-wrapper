package com.myriadcode.fsrs.internal.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonConverter {

    private JsonConverter() {
    }

    public static String toJson(Object object) {
        ObjectMapper mapper = (new ObjectMapper()).registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        if (json == null) {
            throw new NullPointerException("json is marked non-null but is null");
        } else {
            ObjectMapper mapper = (new ObjectMapper()).registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            try {
                return (T) mapper.readValue(json, clazz);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
