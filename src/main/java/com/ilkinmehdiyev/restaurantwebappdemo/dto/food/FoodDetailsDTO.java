package com.ilkinmehdiyev.restaurantwebappdemo.dto.food;

import com.ilkinmehdiyev.restaurantwebappdemo.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class FoodDetailsDTO extends BaseDTO {
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private FoodTypeDTO foodType;
    private Set<IngredientDTO> ingredients;
    private Double calories;
}
