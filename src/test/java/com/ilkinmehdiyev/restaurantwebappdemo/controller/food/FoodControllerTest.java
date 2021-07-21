package com.ilkinmehdiyev.restaurantwebappdemo.controller.food;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ilkinmehdiyev.restaurantwebappdemo.TestsConfiguration;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityCouldNotBeDeletedException;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityNotFoundException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Food;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.FoodDetails;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.FoodType;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.food.FoodService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.ilkinmehdiyev.restaurantwebappdemo.util.Constant.FOOD_URL;
import static com.ilkinmehdiyev.restaurantwebappdemo.util.EntityTools.getJsonString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
        requestObject.setDescription("desc");
        requestObject.setImageUri("image-uri");
        requestObject.setFoodType(new FoodType());
        requestObject.setPrice(BigDecimal.ZERO);
        requestObject.setFoodDetail(new FoodDetails());
        requestBody = getJsonString(requestObject);

        responseObject = new Food();
        responseObject.setName(requestObject.getName());
        responseObject.setDescription(requestObject.getDescription());
        responseObject.setImageUri(requestObject.getImageUri());
        responseObject.setFoodType(requestObject.getFoodType());
        responseObject.setPrice(requestObject.getPrice());
        responseObject.setFoodDetail(requestObject.getFoodDetail());
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

        verify(foodService).getAll();

    }

    @Test
    @DisplayName("GET" + FOOD_URL + "/1")
    public void get_food_by_id_when_exist() throws Exception {
        when(foodService.getById(1L)).thenReturn(responseObject);

        mockMvc.perform(
                get(FOOD_URL + "/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(responseBody));

        verify(foodService).getById(1);
    }

    @Test
    @DisplayName("GET" + FOOD_URL + "/" + Long.MAX_VALUE)
    public void get_food_by_id_when_not_exist() throws Exception {
        when(foodService.getById(Long.MAX_VALUE)).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(
                get(FOOD_URL + "/" + Long.MAX_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(foodService).getById(Long.MAX_VALUE);
    }

    @Test
    @DisplayName("POST" + FOOD_URL)
    public void save_food() throws Exception {
        when(foodService.save(requestObject)).thenReturn(responseObject);

        mockMvc.perform(
                post(FOOD_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(responseBody));

        verify(foodService).save(requestObject);
    }

    @Test
    @DisplayName("PUT" + FOOD_URL + "/1")
    public void update_food_when_id_exist() throws Exception {
        when(foodService.update(1, requestObject)).thenReturn(responseObject);

        mockMvc.perform(
                put(FOOD_URL + "/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(responseBody));

        verify(foodService).update(1L, requestObject);
    }

    @Test
    @DisplayName("PUT" + FOOD_URL + "/" + Long.MAX_VALUE)
    public void update_food_when_id_not_exist() throws Exception {
        when(foodService.update(Long.MAX_VALUE, requestObject)).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(
                put(FOOD_URL + "/" + Long.MAX_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        )
                .andExpect(status().isNotFound());

        verify(foodService).update(Long.MAX_VALUE, requestObject);
    }


    @Test
    @DisplayName("DELETE" + FOOD_URL + "/1")
    public void delete_food_when_id_exist() throws Exception {
        when(foodService.deleteById(1L)).thenReturn(requestObject);

        mockMvc.perform(
                delete(FOOD_URL + "/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(responseBody));

        verify(foodService).deleteById(1L);
    }

    @Test
    @DisplayName("DELETE" + FOOD_URL + "/" + Long.MAX_VALUE)
    public void delete_food_when_id_not_exist() throws Exception {
        when(foodService.deleteById(Long.MAX_VALUE)).thenThrow(EntityCouldNotBeDeletedException.class);

        mockMvc.perform(
                delete(FOOD_URL + "/" + Long.MAX_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(foodService).deleteById(Long.MAX_VALUE);
    }
}