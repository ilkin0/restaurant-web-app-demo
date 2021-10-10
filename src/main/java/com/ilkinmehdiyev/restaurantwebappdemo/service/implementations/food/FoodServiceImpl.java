package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.food;

import com.ilkinmehdiyev.restaurantwebappdemo.dto.food.FoodDTO;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityCouldNotBeDeletedException;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityNotFoundException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Food;
import com.ilkinmehdiyev.restaurantwebappdemo.repo.food.FoodRepo;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.food.FoodService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ilkinmehdiyev.restaurantwebappdemo.util.EntityTools.copyEntityListToDTOList;
import static com.ilkinmehdiyev.restaurantwebappdemo.util.EntityTools.copyEntityPropertiesExpectId;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepo foodRepo;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<FoodDTO> getAll() {
        List<Food> foods = foodRepo.findAll();
        return copyEntityListToDTOList(foods, FoodDTO.class);
    }

    @Override
    public FoodDTO getById(long id) throws EntityNotFoundException {
        Optional<Food> foodOptional = foodRepo.findById(id);
        Food food = foodOptional
                .orElseThrow(() -> new EntityNotFoundException("Entity not found!"));
        return mapper.map(food, FoodDTO.class);
    }

    @Override
    public FoodDTO save(Food food) {
        foodRepo.save(food);
        return mapper.map(food, FoodDTO.class);
    }

    @Override
    public FoodDTO update(long id, Food foodNew) throws EntityNotFoundException {
        Optional<Food> foodById = foodRepo.findById(id);
        Food food = foodById.orElseThrow(() -> new EntityNotFoundException(Food.class, id));

        copyEntityPropertiesExpectId(food, foodNew);
//        foodRepo.save(food);
//        return mapper.map(food, FoodDTO.class);
        return this.save(food);
    }

    @Override
    public FoodDTO deleteById(long id) throws EntityNotFoundException, EntityCouldNotBeDeletedException {
        FoodDTO foodById = this.getById(id);
        try {
            foodRepo.deleteById(id);
            return foodById;
        } catch (Exception e) {
            throw new EntityCouldNotBeDeletedException(Food.class, id, e);
        }
    }
}
