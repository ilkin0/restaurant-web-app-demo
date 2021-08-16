package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.menu;

import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityCouldNotBeDeletedException;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityNotFoundException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Menu;
import com.ilkinmehdiyev.restaurantwebappdemo.repo.menu.MenuRepo;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.menu.MenuService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.ilkinmehdiyev.restaurantwebappdemo.util.EntityTools.copyEntityPropertiesExpectId;

@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepo menuRepo;

    @Override
    public List<Menu> getAll() {
        return menuRepo.findAll();
    }

    @Override
    public Menu getById(long id) throws EntityNotFoundException {
        Optional<Menu> optionalMenu = menuRepo.findById(id);
        return optionalMenu.orElseThrow(() -> new EntityNotFoundException("Entity with " + id + " not found"));
    }

    @Override
    public Menu save(Menu menu) {
        return menuRepo.save(menu);
    }

    @Override
    public Menu update(long id, Menu newMenu) throws EntityNotFoundException {

        Optional<Menu> menuOptional = menuRepo.findById(id);
        Menu menu = menuOptional.orElseThrow(() -> new EntityNotFoundException(Menu.class, id));

        copyEntityPropertiesExpectId(menu, newMenu);
        return menuRepo.save(menu);
    }

    @Override
    public Menu deleteById(long id) throws EntityCouldNotBeDeletedException, EntityNotFoundException {
        Menu menuById = this.getById(id);

        try {
            menuRepo.delete(menuById);
            return menuById;
        } catch (Exception e) {
            throw new EntityCouldNotBeDeletedException(Menu.class, id, e);
        }
    }
}
