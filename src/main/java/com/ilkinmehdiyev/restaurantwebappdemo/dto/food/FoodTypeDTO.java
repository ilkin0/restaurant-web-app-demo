package com.ilkinmehdiyev.restaurantwebappdemo.dto.food;

import com.ilkinmehdiyev.restaurantwebappdemo.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class FoodTypeDTO extends BaseDTO {
    private String name;
}
