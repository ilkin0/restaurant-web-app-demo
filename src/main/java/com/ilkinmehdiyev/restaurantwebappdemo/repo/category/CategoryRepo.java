package com.ilkinmehdiyev.restaurantwebappdemo.repo.category;

import com.ilkinmehdiyev.restaurantwebappdemo.models.Category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
}
