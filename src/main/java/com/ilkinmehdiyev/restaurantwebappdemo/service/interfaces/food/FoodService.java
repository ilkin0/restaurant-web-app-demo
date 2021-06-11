package com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.food;

import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Food;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface FoodService {
    List<Food> getAll();

    Food getById(long id) throws EntityNotFoundException;
}
