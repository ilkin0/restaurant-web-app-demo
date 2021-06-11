package com.ilkinmehdiyev.restaurantwebappdemo.repo.food;

import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepo extends JpaRepository<Food, Long> {
}
