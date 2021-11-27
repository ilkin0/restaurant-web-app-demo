package com.ilkinmehdiyev.restaurantwebappdemo.models.Category;

import com.ilkinmehdiyev.restaurantwebappdemo.models.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
@NoArgsConstructor
public class Category extends BaseEntity {

    private static final long serialVersionUID = 10L;

    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;
}
