package com.ilkinmehdiyev.restaurantwebappdemo.controller.food;

import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityCouldNotBeDeletedException;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityNotFoundException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Food;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.food.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ilkinmehdiyev.restaurantwebappdemo.util.Constant.FOOD_URL;

@RestController("FoodController")
@RequestMapping(FOOD_URL)
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @GetMapping
    public List<Food> getAll() {
        return foodService.getAll();
    }

    @GetMapping("{id}")
    public Food getById(@PathVariable long id) throws EntityNotFoundException {
        return foodService.getById(id);
    }

    @PostMapping
    public Food save(@RequestBody Food food) {
        return foodService.save(food);
    }

    @PutMapping("{id}")
    public Food update(@PathVariable long id, @RequestBody Food food) throws EntityNotFoundException {
        return foodService.update(id, food);
    }

    @DeleteMapping("{id}")
    public Food deleteById(@PathVariable long id) throws EntityCouldNotBeDeletedException, EntityNotFoundException {
        return foodService.deleteById(id);
    }


}
