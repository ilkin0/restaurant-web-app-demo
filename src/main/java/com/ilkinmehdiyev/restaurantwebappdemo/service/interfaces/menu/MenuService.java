package com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.menu;

import com.ilkinmehdiyev.restaurantwebappdemo.dto.food.MenuDTO;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityCouldNotBeDeletedException;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityNotFoundException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Menu;

import java.util.List;

public interface MenuService {
    List<MenuDTO> getAll();

    MenuDTO getById(long id) throws EntityNotFoundException;

    MenuDTO save(Menu menu);

    MenuDTO update(long id, Menu newMenu) throws EntityNotFoundException;

    MenuDTO deleteById(long id) throws EntityCouldNotBeDeletedException, EntityNotFoundException;
}
