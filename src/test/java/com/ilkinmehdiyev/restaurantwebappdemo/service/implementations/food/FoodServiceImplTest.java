package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.food;

import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Food;
import com.ilkinmehdiyev.restaurantwebappdemo.repo.food.FoodRepo;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.food.FoodService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FoodServiceImplTest {

    private static FoodRepo foodRepo;
    private static FoodService foodService;

    private static Food passed;
    private static Food expected;

    @BeforeAll
    public static void init() {
        foodRepo = mock(FoodRepo.class);
        foodService = new FoodServiceImpl(foodRepo);

        expected = new Food();
    }


    @Test
    @DisplayName("getAll()")
    public void getAll() {
        List<Food> foods = new ArrayList<>();
        foods.add(expected);

        when(foodRepo.findAll()).thenReturn(foods);
        List<Food> result = foodService.getAll();

        assertEquals(foods, result);
    }

    @Test
    @DisplayName("findById(id)")
    public void find_by_id_when_exists() {
        when(foodRepo.findById(1L)).thenReturn(Optional.of(expected));

        Food byId = foodService.getById(1L);
        assertEquals(expected, byId);
    }

    @Test
    @DisplayName("findById(LONG.MAX_VALUE)")
    public void find_by_id_when_not_exists() {
        when(foodRepo.findById(Long.MAX_VALUE))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                foodService.getById(Long.MAX_VALUE));
    }


}