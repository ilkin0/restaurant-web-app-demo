package com.ilkinmehdiyev.restaurantwebappdemo.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "INGREDIENT")
public class Ingredient extends BaseEntity {
    private static final long serialVersionUID = 6L;

    private String name;
    private double calories;
    private double protein;
    private double carbs;
    private double sugar;
    private double fiber;
}
