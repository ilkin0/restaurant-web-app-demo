package com.ilkinmehdiyev.restaurantwebappdemo.controller.food;

import com.ilkinmehdiyev.restaurantwebappdemo.dto.food.FoodDTO;
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
    public List<FoodDTO> getAll() {
        return foodService.getAll();
    }

    @GetMapping("/{id}")
    public FoodDTO getById(@PathVariable("id") long id) throws EntityNotFoundException {
        return foodService.getById(id);
    }

    @PostMapping
    public FoodDTO save(@RequestBody Food food) {
        return foodService.save(food);
    }

    @PutMapping("/{id}")
    public FoodDTO update(@PathVariable long id, @RequestBody Food food) throws EntityNotFoundException {
        return foodService.update(id, food);
    }

    @DeleteMapping("/{id}")
    public FoodDTO deleteById(@PathVariable long id) throws EntityCouldNotBeDeletedException, EntityNotFoundException {
        return foodService.deleteById(id);
    }


}
