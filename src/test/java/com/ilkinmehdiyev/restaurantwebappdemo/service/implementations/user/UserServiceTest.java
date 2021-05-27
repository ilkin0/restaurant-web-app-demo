package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.user;

import com.ilkinmehdiyev.restaurantwebappdemo.models.Food;
import com.ilkinmehdiyev.restaurantwebappdemo.models.User;
import com.ilkinmehdiyev.restaurantwebappdemo.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    private UserService userService;
    private User passed;
    private User expected;
    private Food food;

    @BeforeEach
    public void setUp() {
//        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepo);

        food = new Food();
        food.setId(1L);
        food.setName("Steak");


        passed = new User();
        passed.setAge(25);
        passed.setFirstName("Mohammad");
        passed.setLastName("Ahmadi");
        passed.setFavorites(Collections.singletonList(food));
        passed.setAllergic(Collections.singletonList(food));

        expected = new User();
        expected.setId(1L);
        expected.setAge(passed.getAge());
        expected.setFirstName(passed.getFirstName());
        expected.setLastName(passed.getLastName());
        expected.setFavorites(passed.getFavorites());
        expected.setAllergic(passed.getAllergic());
    }


    @Test
    @DisplayName("findUserByEmail(email)")
    public void find_user_by_email_when_exist() {
        when(userRepo.findByEmail(anyString()))
                .thenReturn(Optional.of(expected));

        UserDetails userByUsername = userService.loadUserByUsername(anyString());
        assertEquals(expected, userByUsername);
    }
}