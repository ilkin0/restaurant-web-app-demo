package com.ilkinmehdiyev.restaurantwebappdemo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ilkinmehdiyev.restaurantwebappdemo.models.BaseEntity;
import org.springframework.beans.BeanUtils;

public class EntityTools {

    public static String getJsonString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper.writeValueAsString(object);
    }

    public static void copyEntityPropertiesExpectId(BaseEntity target, BaseEntity source) {
        long targetId = target.getId();
        BeanUtils.copyProperties(source, target);
        target.setId(targetId);
    }
}
