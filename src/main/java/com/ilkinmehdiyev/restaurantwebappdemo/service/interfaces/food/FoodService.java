package com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.food;

import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityCouldNotBeDeletedException;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityNotFoundException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Food;

import java.util.List;

public interface FoodService {
    List<Food> getAll();

    Food getById(long id) throws EntityNotFoundException;

    Food save(Food food);

    Food update(long id, Food foodNew) throws EntityNotFoundException;

    Food deleteById(long id) throws EntityCouldNotBeDeletedException, EntityNotFoundException;
}
