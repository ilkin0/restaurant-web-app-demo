package com.ilkinmehdiyev.restaurantwebappdemo.dto.food;


import com.ilkinmehdiyev.restaurantwebappdemo.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class FoodDTO extends BaseDTO {
    private String name;
    private String description;
    private String imageUri;
    private FoodTypeDTO foodType;
    private int portion;
    private BigDecimal price;
    private FoodDetailsDTO foodDetails;
    private Set<MenuDTO> menuSet;
}
