package com.ilkinmehdiyev.restaurantwebappdemo.controller.food;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ilkinmehdiyev.restaurantwebappdemo.TestsConfiguration;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Food;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.food.FoodService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.ilkinmehdiyev.restaurantwebappdemo.util.Constant.FOOD_URL;
import static com.ilkinmehdiyev.restaurantwebappdemo.util.UtilTools.getJsonString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class FoodControllerTest extends TestsConfiguration {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FoodService foodService;

    private static Food requestObject;
    private static String requestBody;

    private static Food responseObject;
    private static String responseBody;

    @BeforeAll
    public static void init() throws JsonProcessingException {
        requestObject = new Food();
        requestObject.setName("name");
        requestBody = getJsonString(requestObject);

        responseObject = new Food();
        responseObject.setName("name");
        responseBody = getJsonString(responseObject);

    }


    @Test
    @DisplayName("GET" + FOOD_URL)
    public void get_all() throws Exception {
        List<Food> foodList = new ArrayList<>();
        foodList.add(responseObject);

        when(foodService.getAll()).thenReturn(foodList);

        mockMvc.perform(
                get(FOOD_URL).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(getJsonString(foodList)));

    }
}