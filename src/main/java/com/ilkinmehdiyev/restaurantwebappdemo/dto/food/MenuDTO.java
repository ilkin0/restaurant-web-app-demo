package com.ilkinmehdiyev.restaurantwebappdemo.dto.food;

import com.ilkinmehdiyev.restaurantwebappdemo.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class MenuDTO extends BaseDTO {
    private Set<FoodDTO> foods;
}
