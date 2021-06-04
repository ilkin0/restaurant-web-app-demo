package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.user;

import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Food;
import com.ilkinmehdiyev.restaurantwebappdemo.models.User.ApplicationUser;
import com.ilkinmehdiyev.restaurantwebappdemo.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ApplicationUserServiceImplTest {

    @Mock
    private UserRepo userRepo;

    private UserServiceImpl userServiceImpl;
    private ApplicationUser passed;
    private ApplicationUser expected;
    private Food food;

    @BeforeEach
    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        userService = new UserService(userRepo);

        food = new Food();
        food.setId(1L);
        food.setName("Steak");


        passed = new ApplicationUser();
        passed.setAge(25);
        passed.setFirstName("Mohammad");
        passed.setLastName("Ahmadi");
//        passed.setFavorites(Collections.singletonList(food));
//        passed.setAllergic(Collections.singletonList(food));

        expected = new ApplicationUser();
        expected.setId(1L);
        expected.setAge(passed.getAge());
        expected.setFirstName(passed.getFirstName());
        expected.setLastName(passed.getLastName());
//        expected.setFavorites(passed.getFavorites());
//        expected.setAllergic(passed.getAllergic());
    }


    @Test
    @DisplayName("findUserByEmail(email)")
    public void find_user_by_email_when_exist() {
        when(userRepo.findByEmail(anyString()))
                .thenReturn(Optional.of(expected));

        UserDetails userByUsername = userServiceImpl.loadUserByUsername(anyString());
        assertEquals(expected, userByUsername);
    }
}