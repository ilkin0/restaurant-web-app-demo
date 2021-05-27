package com.ilkinmehdiyev.restaurantwebappdemo.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USER")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

    private static final long serialVersionUID = 4L;

    private String firstName;
    private String lastName;
    private int age;
    private String profilePictureUrl;
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Food> favorites;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Food> allergic;
}
