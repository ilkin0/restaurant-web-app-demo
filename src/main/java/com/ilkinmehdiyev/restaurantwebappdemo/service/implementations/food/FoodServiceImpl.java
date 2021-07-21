package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.food;

import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityCouldNotBeDeletedException;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityNotFoundException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Food;
import com.ilkinmehdiyev.restaurantwebappdemo.repo.food.FoodRepo;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.food.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ilkinmehdiyev.restaurantwebappdemo.util.EntityTools.copyEntityPropertiesExpectId;

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

    @Override
    public Food save(Food food) {
        return foodRepo.save(food);
    }

    @Override
    public Food update(long id, Food foodNew) throws EntityNotFoundException {
        Optional<Food> foodById = foodRepo.findById(id);
        Food food = foodById.orElseThrow(() -> new EntityNotFoundException(Food.class, id));

        copyEntityPropertiesExpectId(food, foodNew);
        return foodRepo.save(food);
    }

    @Override
    public Food deleteById(long id) throws EntityNotFoundException, EntityCouldNotBeDeletedException {
        Food foodById = this.getById(id);
        try {
            foodRepo.deleteById(id);
            return foodById;
        } catch (Exception e) {
            throw new EntityCouldNotBeDeletedException(Food.class, id, e);
        }
    }
}
