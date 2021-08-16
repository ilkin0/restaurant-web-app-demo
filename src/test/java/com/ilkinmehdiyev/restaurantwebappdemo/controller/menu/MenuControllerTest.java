package com.ilkinmehdiyev.restaurantwebappdemo.controller.menu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ilkinmehdiyev.restaurantwebappdemo.TestsConfiguration;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EntityNotFoundException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Food;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Menu;
import com.ilkinmehdiyev.restaurantwebappdemo.repo.menu.MenuRepo;
import com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.menu.MenuServiceImpl;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.menu.MenuService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static com.ilkinmehdiyev.restaurantwebappdemo.util.Constant.MENU_URL;
import static com.ilkinmehdiyev.restaurantwebappdemo.util.EntityTools.getJsonString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MenuControllerTest extends TestsConfiguration {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private MenuService menuService;

    private static MenuRepo menuRepo;

    private static Menu requestObject;
    private static Menu responseObject;

    private static String requestBody;
    private static String responseBody;

    @BeforeAll
    public static void init() throws JsonProcessingException {

//        menuRepo = mock(MenuRepo.class);
//        menuService = new MenuServiceImpl(menuRepo);

        Food food = new Food();
        food.setId(1L);
        food.setName("Pizza");

        requestObject = new Menu();
        requestObject.setId(1L);
        requestObject.setFoods(Set.of(food));

        responseObject = new Menu();
        responseObject.setId(requestObject.getId());
        responseObject.setFoods(requestObject.getFoods());

        requestBody = getJsonString(requestObject);
        responseBody = getJsonString(responseObject);
    }


    @Test
    @DisplayName("GET" + MENU_URL)
    public void find_all() throws Exception {
        List<Menu> menuList = List.of(responseObject);
        when(menuService.getAll()).thenReturn(menuList);

        mockMvc.perform(
                        get(MENU_URL).accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(getJsonString(menuList)));
    }

    @Test
    @DisplayName("GET" + MENU_URL + "/1")
    public void find_by_id_when_exist() throws Exception {
        when(menuService.getById(1L)).thenReturn(responseObject);

        mockMvc.perform(
                        get(MENU_URL + "/1").accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(responseBody));

        verify(menuService).getById(1L);
    }

    @Test
    @DisplayName("GET" + MENU_URL + "/" + Long.MAX_VALUE)
    public void find_all_when_not_exist() throws Exception {
        when(menuService.getById(Long.MAX_VALUE)).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(
                        get(MENU_URL + "/" + Long.MAX_VALUE).accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());

        verify(menuService).getById(1L);
    }

}