package com.ilkinmehdiyev.restaurantwebappdemo.repo.menu;

import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepo extends JpaRepository<Menu, Long> {
}
