package com.ilkinmehdiyev.restaurantwebappdemo.controller.menu;

import com.ilkinmehdiyev.restaurantwebappdemo.dto.food.MenuDTO;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityCouldNotBeDeletedException;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityNotFoundException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Menu;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

import static com.ilkinmehdiyev.restaurantwebappdemo.util.Constant.MENU_URL;

@RestController("MenuController")
@RequestMapping(MENU_URL)
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @GetMapping
    public List<MenuDTO> findAll() {
        return menuService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MenuDTO getById(@PathVariable long id) throws EntityNotFoundException {
        return menuService.getById(id);
    }

    @PostMapping
    public MenuDTO save(@RequestBody Menu menu) {
        return menuService.save(menu);
    }

    @PutMapping("{/id}")
    public MenuDTO update(
            @PathVariable long id,
            @RequestBody Menu menu
    ) throws EntityNotFoundException {
        return menuService.update(id, menu);
    }

    @DeleteMapping("/{id}")
    public MenuDTO delete(@PathVariable long id) throws EntityCouldNotBeDeletedException, EntityNotFoundException {
        return menuService.deleteById(id);
    }
}
