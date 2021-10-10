package com.ilkinmehdiyev.restaurantwebappdemo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ilkinmehdiyev.restaurantwebappdemo.dto.BaseDTO;
import com.ilkinmehdiyev.restaurantwebappdemo.models.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntityTools {

    private static final ModelMapper mapper = new ModelMapper();

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

    public static <T> List<T> copyEntityListToDTOList(List<?> source, Class<T> destination) {
        return source.stream()
                .map(e -> mapper.map(e, destination))
                .collect(Collectors.toList());
    }
}
