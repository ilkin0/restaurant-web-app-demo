package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.food;

import com.ilkinmehdiyev.restaurantwebappdemo.dto.food.FoodDTO;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityCouldNotBeDeletedException;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityNotFoundException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Food;
import com.ilkinmehdiyev.restaurantwebappdemo.repo.food.FoodRepo;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.food.FoodService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ilkinmehdiyev.restaurantwebappdemo.util.EntityTools.copyEntityListToDTOList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FoodServiceImplTest {

    private static FoodRepo foodRepo;
    private static FoodService foodService;
    private static ModelMapper mapper;

    private static FoodDTO passedDTO;
    private static FoodDTO expectedDTO;
    private static Food passed;
    private static Food expected;

    @BeforeAll
    public static void init() {
        foodRepo = mock(FoodRepo.class);
        foodService = new FoodServiceImpl(foodRepo);

        passed = new Food();
        passed.setId(1);
        passedDTO = new FoodDTO();
        passedDTO.setId(passed.getId());

        expected = new Food();
        expected.setId(passed.getId());
        expectedDTO = new FoodDTO();
            expectedDTO.setId(expected.getId());
    }


    @Test
    @DisplayName("getAll()")
    public void getAll() {
        List<Food> foods = new ArrayList<>();
        foods.add(expected);

        when(foodRepo.findAll()).thenReturn(foods);

        List<FoodDTO> foodDTOs = copyEntityListToDTOList(foods, FoodDTO.class);
        List<FoodDTO> result = foodService.getAll();

        assertEquals(foodDTOs, result);
    }

    @Test
    @DisplayName("findById(id)")
    public void find_by_id_when_exists() throws EntityNotFoundException {
        when(foodRepo.findById(1L)).thenReturn(Optional.of(expected));

        FoodDTO byId = foodService.getById(1L);
        assertEquals(expectedDTO, byId);
    }

    @Test
    @DisplayName("findById(" + Long.MAX_VALUE + ")")
    public void find_by_id_when_not_exists() {
        when(foodRepo.findById(Long.MAX_VALUE))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                foodService.getById(Long.MAX_VALUE));
    }

    @Test
    @DisplayName("save(Food)")
    public void save_food() {
        when(foodRepo.save(passed))
                .thenReturn(expected);

        FoodDTO saved = foodService.save(passed);
        assertEquals(expectedDTO, saved);
    }

    @Test
    @DisplayName("update(1, foodNew)")
    public void update_food_when_exists() throws EntityNotFoundException {
        when(foodRepo.findById(1L))
                .thenReturn(Optional.of(expected));

        when(foodRepo.save(passed))
                .thenReturn(expected);


        FoodDTO update = foodService.update(1L, passed);
        assertEquals(update, expectedDTO);

        // TODO didnt pass
    }

    @Test
    @DisplayName("update(" + Long.MAX_VALUE + ", foodNew)")
    public void update_food_when_id_not_exists() {
        when(foodRepo.findById(Long.MAX_VALUE))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> foodService.update(Long.MAX_VALUE, passed));
    }

    @Test
    @DisplayName("delete(1)")
    public void delete_food_when_id_exists() throws EntityNotFoundException, EntityCouldNotBeDeletedException {
        when(foodRepo.findById(1L))
                .thenReturn(Optional.of(expected));
        FoodDTO delete = foodService.deleteById(1L);
        assertEquals(expectedDTO, delete);
    }

    @Test
    @DisplayName("delete(" + Long.MAX_VALUE + ")")
    public void delete_food_when_id_not_exists() {

        when(foodRepo.findById(Long.MAX_VALUE))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> foodService.deleteById(Long.MAX_VALUE));
    }
}