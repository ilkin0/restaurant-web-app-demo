package com.ilkinmehdiyev.restaurantwebappdemo.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "FOOD")
public class Food extends BaseEntity {


    private String name;
}
