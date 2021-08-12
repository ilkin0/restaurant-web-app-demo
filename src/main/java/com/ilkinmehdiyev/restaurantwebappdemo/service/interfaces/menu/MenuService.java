package com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.menu;

import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityNotFoundException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getAll();

    Menu getById(long id) throws EntityNotFoundException;

    Menu save(Menu menu);

    Menu update(long id, Menu newMenu) throws EntityNotFoundException;
}
