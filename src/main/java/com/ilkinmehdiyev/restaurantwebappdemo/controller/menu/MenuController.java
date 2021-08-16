package com.ilkinmehdiyev.restaurantwebappdemo.controller.menu;

import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityCouldNotBeDeletedException;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityNotFoundException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Menu;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ilkinmehdiyev.restaurantwebappdemo.util.Constant.MENU_URL;

@RestController("MenuController")
@RequestMapping(MENU_URL)
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @GetMapping
    public List<Menu> findAll() {
        return menuService.getAll();
    }

    @GetMapping("{/id}")
    public Menu findById(@PathVariable long id) throws EntityNotFoundException {
        return menuService.getById(id);
    }

    @PostMapping
    public Menu save(@RequestBody Menu menu) {
        return menuService.save(menu);
    }

    @PutMapping("{/id}")
    public Menu update(
            @PathVariable long id,
            @RequestBody Menu menu
    ) throws EntityNotFoundException {
        return menuService.update(id, menu);
    }

    @DeleteMapping("{/id}")
    public Menu delete(@PathVariable long id) throws EntityCouldNotBeDeletedException, EntityNotFoundException {
        return menuService.deleteById(id);
    }
}
