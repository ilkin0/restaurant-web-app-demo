package com.ilkinmehdiyev.restaurantwebappdemo.dto.category;

import com.ilkinmehdiyev.restaurantwebappdemo.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryDTO extends BaseDTO {
    private String name;
    private String description;
}
