package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.menu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilkinmehdiyev.restaurantwebappdemo.dto.food.MenuDTO;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityCouldNotBeDeletedException;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityNotFoundException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Menu;
import com.ilkinmehdiyev.restaurantwebappdemo.repo.menu.MenuRepo;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.menu.MenuService;
import com.ilkinmehdiyev.restaurantwebappdemo.util.EntityTools;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ilkinmehdiyev.restaurantwebappdemo.util.EntityTools.copyEntityListToDTOList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MenuServiceImplTest {

    private static MenuRepo menuRepo;
    private static MenuService menuService;
    private static ModelMapper mapper;

    private static Menu passed;
    private static Menu expected;

    private static MenuDTO passedDTO;
    private static MenuDTO expectedDTO;

    @BeforeAll
    public static void init() {
        menuRepo = mock(MenuRepo.class);
        menuService = new MenuServiceImpl(menuRepo);
        mapper = new ModelMapper();

        passed = new Menu();
        passed.setId(1L);
        passedDTO = mapper.map(passed, MenuDTO.class);

        expected = new Menu();
        expected.setId(passed.getId());

        expectedDTO = mapper.map(expected, MenuDTO.class);
    }


    @Test
    @DisplayName("getAll()")
    public void get_all_menus() {
        List<Menu> menuList = new ArrayList<>();
        menuList.add(passed);

        when(menuRepo.findAll()).thenReturn(menuList);

        List<MenuDTO> result = menuService.getAll();
        List<MenuDTO> menuDTOS = copyEntityListToDTOList(menuList, MenuDTO.class);

        assertEquals(menuDTOS, result);
        verify(menuRepo).findAll();
    }

    @Test
    @DisplayName("findById(id)")
    public void find_menu_by_id_when_exist() throws EntityNotFoundException {
        when(menuRepo.findById(1L)).thenReturn(Optional.of(passed));

        MenuDTO menuById = menuService.getById(1L);
        assertEquals(passedDTO, menuById);
    }

    @Test
    @DisplayName("findById(" + Long.MAX_VALUE + ")")
    public void find_menu_by_id_when_not_exist() {
        when(menuRepo.findById(Long.MAX_VALUE)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                menuService.getById(Long.MAX_VALUE));
    }

    @Test
    @DisplayName("save(menu)")
    public void save_menu() {
        when(menuRepo.save(passed)).thenReturn(expected);

        MenuDTO saved = menuService.save(passed);
        assertEquals(saved, passedDTO);
    }

    @Test
    @DisplayName("update(1L, menu)")
    public void update_menu_when_exist() throws EntityNotFoundException {
        when(menuRepo.findById(1L)).thenReturn(Optional.of(expected));
        when(menuRepo.save(passed)).thenReturn(expected);

        MenuDTO update = menuService.update(1L, passed);
        assertEquals(update, expectedDTO);
    }

    @Test
    @DisplayName("update(" + Long.MAX_VALUE + ")")
    public void update_menu_when_not_exist() {
        when(menuRepo.findById(Long.MAX_VALUE)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                menuService.update(Long.MAX_VALUE, passed));
    }

    @Test
    @DisplayName("delete(id)")
    public void delete_when_menu_exist() throws EntityNotFoundException {
        when(menuRepo.findById(1L)).thenReturn(Optional.of(expected));
        MenuDTO deleteById = null;
        try {
            deleteById = menuService.deleteById(1L);
        } catch (EntityCouldNotBeDeletedException e) {
            e.printStackTrace();
        }

        assertEquals(deleteById, passedDTO);
    }

    @Test
    @DisplayName("delete(" + Long.MAX_VALUE + ")")
    public void delete_when_not_exist() {
        when(menuRepo.findById(Long.MAX_VALUE)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () ->
                menuService.deleteById(Long.MAX_VALUE));
    }
}