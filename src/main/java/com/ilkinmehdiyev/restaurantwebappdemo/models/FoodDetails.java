package com.ilkinmehdiyev.restaurantwebappdemo.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "FOOD_DETAIL")
@Getter
@Setter
@NoArgsConstructor
public class FoodDetails extends BaseEntity {

    private static final long serialVersionUID = 5L;

    @OneToOne(fetch = FetchType.EAGER)
    private FoodType foodType;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Ingredient> ingredients;

    private double calories;
}
