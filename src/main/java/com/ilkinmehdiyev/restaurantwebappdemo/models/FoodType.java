package com.ilkinmehdiyev.restaurantwebappdemo.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "FOOD_TYPE")
public class FoodType extends BaseEntity {

    private static final long serialVersionUID = 2L;

    @Column(name = "NAME", length = 25)
    private String name;
}
