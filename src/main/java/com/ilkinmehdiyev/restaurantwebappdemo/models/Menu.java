package com.ilkinmehdiyev.restaurantwebappdemo.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MENU")
@Getter
@Setter
@NoArgsConstructor
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 3L;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<Food> foods = new HashSet<>();

}
