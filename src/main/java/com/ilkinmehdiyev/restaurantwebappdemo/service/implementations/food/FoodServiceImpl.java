package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.food;

import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Food;
import com.ilkinmehdiyev.restaurantwebappdemo.repo.food.FoodRepo;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.food.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepo foodRepo;

    @Override
    public List<Food> getAll() {
        return foodRepo.findAll();
    }

    @Override
    public Food getById(long id) throws EntityNotFoundException {
        Optional<Food> foodOptional = foodRepo.findById(id);
        return foodOptional.orElseThrow(() -> new EntityNotFoundException("Entity not found!"));
    }
}
