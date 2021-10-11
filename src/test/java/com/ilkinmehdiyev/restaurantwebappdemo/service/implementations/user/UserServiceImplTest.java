package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.user;

import com.ilkinmehdiyev.restaurantwebappdemo.exception.UserAlreadyExistsException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Food.Food;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Token.ConfirmationToken;
import com.ilkinmehdiyev.restaurantwebappdemo.models.User.ApplicationUser;
import com.ilkinmehdiyev.restaurantwebappdemo.repo.confirmationtoken.ConfirmationTokenRepo;
import com.ilkinmehdiyev.restaurantwebappdemo.repo.user.UserRepo;
import com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.confirmationtoken.ConfirmationTokenServiceImpl;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.confirmationtoken.ConfirmationTokenService;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ApplicationUserServiceImplTest {

//    TODO Unit testing not working at all
    @Mock
    private UserRepo userRepo;
    private UserService userService;
    private UserDetailsService userDetailsService;
    private ConfirmationTokenRepo tokenRepo;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ConfirmationTokenService tokenService;
    private Environment environment;
    private ModelMapper mapper;

    private ApplicationUser passed;
    private ApplicationUser expected;
    private ConfirmationToken passedToken;
    private ConfirmationToken expectedToken;
    private Food food;

    @BeforeEach
    public void setUp() {
        userRepo = mock(UserRepo.class);
        tokenRepo = mock(ConfirmationTokenRepo.class);

        tokenService = new ConfirmationTokenServiceImpl(tokenRepo, mapper);
        userService = new UserServiceImpl(userRepo, bCryptPasswordEncoder, tokenService, environment);
        userDetailsService = mock(UserDetailsService.class);

        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        environment = mock(Environment.class);

        food = new Food();
        food.setId(1L);
        food.setName("Steak");


        passed = new ApplicationUser();
        passed.setAge(25);
        passed.setFirstName("Mohammad");
        passed.setLastName("Ahmadi");
        passed.setEmail("test@gmail.com");
        passed.setPassword("password");
//        passed.setFavorites(Collections.singletonList(food));
//        passed.setAllergic(Collections.singletonList(food));

        expected = new ApplicationUser();
        expected.setId(1L);
        expected.setAge(passed.getAge());
        expected.setFirstName(passed.getFirstName());
        expected.setLastName(passed.getLastName());
        expected.setEmail(passed.getEmail());
        expected.setPassword(passed.getPassword());
//        expected.setFavorites(passed.getFavorites());
//        expected.setAllergic(passed.getAllergic());

        passedToken = new ConfirmationToken(
                "token",
                LocalDateTime.now(),
                passed
        );

        expectedToken = new ConfirmationToken();
        expectedToken.setToken(passedToken.getToken());
        expectedToken.setExpiresAt(passedToken.getExpiresAt());
        expectedToken.setUser(passedToken.getUser());
    }


    @Test
    @DisplayName("loadUserByUsername(email)")
    public void find_user_by_email_when_exist() {
        when(userRepo.findByEmail(anyString()))
                .thenReturn(Optional.of(expected));

        UserDetails userByUsername = userService.loadUserByUsername(anyString());
        assertEquals(expected, userByUsername);
    }

//    @Test
//    @DisplayName("loadUserByUsername(wrong_email)")
//    public void find_user_by_email_when_not_exist() {
//        when(userRepo.findByEmail(anyString()).isEmpty())
//                .thenThrow(new IllegalAccessError());
//
//        assertThrows(UsernameNotFoundException.class, () ->
//                userDetailsService.loadUserByUsername(""));
//    }

//    @Test
//    @DisplayName("signUpUser(user)")
//    public void signup_user_when_user_not_exist() throws UserAlreadyExistsException {
//        when(userRepo.findByEmail(passed.getEmail()))
//                .thenReturn(Optional.of(passed));
//
//        when(userRepo.save(passed))
//                .thenReturn(expected);
//
//        when(tokenRepo.save(passedToken))
//                .thenReturn(expectedToken);
//
//        String signUpUser = userService.signUpUser(passed);
//        assertEquals(signUpUser, passed);
//    }

    @Test
    @DisplayName("enableUser()")
    public void enable_user_when_disabled() {
        when(userRepo.enableUser(passed.getEmail()))
                .thenReturn(1);

        int i = userService.enableUser(passed.getEmail());
        assertEquals(i, 1);
    }
}