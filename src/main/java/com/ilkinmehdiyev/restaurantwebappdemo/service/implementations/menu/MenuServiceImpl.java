package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.menu;

import com.ilkinmehdiyev.restaurantwebappdemo.dto.food.MenuDTO;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityCouldNotBeDeletedException;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityNotFoundException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Menu;
import com.ilkinmehdiyev.restaurantwebappdemo.repo.menu.MenuRepo;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ilkinmehdiyev.restaurantwebappdemo.util.EntityTools.copyEntityListToDTOList;
import static com.ilkinmehdiyev.restaurantwebappdemo.util.EntityTools.copyEntityPropertiesExpectId;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepo menuRepo;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<MenuDTO> getAll() {
        List<Menu> menus = menuRepo.findAll();
        return copyEntityListToDTOList(menus, MenuDTO.class);
    }

    @Override
    public MenuDTO getById(long id) throws EntityNotFoundException {
        Optional<Menu> optionalMenu = menuRepo.findById(id);
        Menu menu = optionalMenu
                .orElseThrow(() -> new EntityNotFoundException("Entity with " + id + " not found"));
        return mapper.map(menu, MenuDTO.class);
    }

    @Override
    public MenuDTO save(Menu menu) {
        menuRepo.save(menu);
        return mapper.map(menu, MenuDTO.class);
    }

    @Override
    public MenuDTO update(long id, Menu newMenu) throws EntityNotFoundException {

        Optional<Menu> menuOptional = menuRepo.findById(id);
        Menu menu = menuOptional.orElseThrow(() -> new EntityNotFoundException(Menu.class, id));

        copyEntityPropertiesExpectId(menu, newMenu);
//        return menuRepo.save(menu);
        return this.save(menu);
    }

    @Override
    public MenuDTO deleteById(long id) throws EntityCouldNotBeDeletedException, EntityNotFoundException {
        MenuDTO menuById = this.getById(id);

        try {
            menuRepo.deleteById(id);
            return menuById;
        } catch (Exception e) {
            throw new EntityCouldNotBeDeletedException(Menu.class, id, e);
        }
    }
}
