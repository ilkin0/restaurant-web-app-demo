package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.menu;

import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityNotFoundException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Menu;
import com.ilkinmehdiyev.restaurantwebappdemo.repo.menu.MenuRepo;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.menu.MenuService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MenuServiceImplTest {

    private static MenuRepo menuRepo;
    private static MenuService menuService;

    private static Menu passed;
    private static Menu expected;

    @BeforeAll
    public static void init() {
        menuRepo = mock(MenuRepo.class);
        menuService = new MenuServiceImpl(menuRepo);

        passed = new Menu();

        expected = new Menu();
    }


    @Test
    @DisplayName("getAll()")
    public void get_all_menus() {
        List<Menu> menuList = new ArrayList<>();
        menuList.add(passed);

        when(menuRepo.findAll()).thenReturn(menuList);

        List<Menu> menus = menuService.getAll();
        assertEquals(menuList, menus);
        verify(menuRepo).findAll();
    }

    @Test
    @DisplayName("findById(id)")
    public void find_menu_by_id_when_exist() throws EntityNotFoundException {
        when(menuRepo.findById(1L)).thenReturn(Optional.of(passed));

        Menu menuById = menuService.getById(1L);
        assertEquals(passed, menuById);
    }

    @Test
    @DisplayName("findById(" + Long.MAX_VALUE + ")")
    public void find_menu_by_id_when_not_exist() {
        when(menuRepo.findById(Long.MAX_VALUE)).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () ->
                menuService.getById(Long.MAX_VALUE));
    }

    @Test
    @DisplayName("save(menu)")
    public void save_menu() {
        when(menuRepo.save(passed)).thenReturn(expected);

        Menu saved = menuService.save(passed);
        assertEquals(saved, passed);
    }

    @Test
    @DisplayName("update(1L, menu)")
    public void update_menu_when_exist() throws EntityNotFoundException {
        when(menuRepo.findById(1L)).thenReturn(Optional.of(expected));
        when(menuRepo.save(passed)).thenReturn(expected);

        Menu update = menuService.update(1L, passed);
        assertEquals(update, expected);
    }

    @Test
    @DisplayName("update(" + Long.MAX_VALUE + ")")
    public void update_menu_when_not_exist() {
        when(menuRepo.findById(Long.MAX_VALUE)).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () ->
                menuService.update(Long.MAX_VALUE, passed));
    }
}