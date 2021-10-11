package com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.food;

import com.ilkinmehdiyev.restaurantwebappdemo.dto.food.FoodDTO;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityCouldNotBeDeletedException;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityNotFoundException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Food;

import java.util.List;

public interface FoodService {
    List<FoodDTO> getAll();

    FoodDTO getById(long id) throws EntityNotFoundException;

    FoodDTO save(Food food);

    FoodDTO update(long id, Food foodNew) throws EntityNotFoundException;

    FoodDTO deleteById(long id) throws EntityCouldNotBeDeletedException, EntityNotFoundException;
}
