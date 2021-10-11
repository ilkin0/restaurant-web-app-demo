package com.ilkinmehdiyev.restaurantwebappdemo.dto.food;

import com.ilkinmehdiyev.restaurantwebappdemo.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class IngredientDTO extends BaseDTO {
    private String name;
    private double calories;
    private double protein;
    private double carbs;
    private double sugar;
    private double fiber;
}
