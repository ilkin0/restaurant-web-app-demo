package com.ilkinmehdiyev.restaurantwebappdemo.models.Food;

import com.ilkinmehdiyev.restaurantwebappdemo.models.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "FOOD")
public class Food extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @Column(name = "IMAGE_URI", length = 1000)
    private String imageUri;

    @OneToOne(fetch = FetchType.EAGER)
    private FoodType foodType;

    @Column(name = "PORTION", columnDefinition = "INT")
    private int portion;

    @Column(name = "PRICE", columnDefinition = "INT")
    private BigDecimal price;

    @OneToOne(fetch = FetchType.EAGER)
    private FoodDetails foodDetail;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "foods", cascade = CascadeType.PERSIST)
    private Set<Menu> menus = new HashSet<>();
}
