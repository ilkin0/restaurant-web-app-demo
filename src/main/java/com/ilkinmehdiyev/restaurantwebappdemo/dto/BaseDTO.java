package com.ilkinmehdiyev.restaurantwebappdemo.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "id")
@Data
public abstract class BaseDTO {
    private Long id;
}
