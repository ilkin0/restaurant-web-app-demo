package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.registration;

import com.ilkinmehdiyev.restaurantwebappdemo.dto.userregistration.UserRegistrationRequestDTO;
import com.ilkinmehdiyev.restaurantwebappdemo.repo.user.UserRepo;
import com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.user.UserServiceImpl;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.confirmationtoken.ConfirmationTokenService;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.email.EmailService;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.user.UserService;
import com.ilkinmehdiyev.restaurantwebappdemo.util.EmailTools;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class UserRegistrationServiceImplTest {

    private static UserService userService;
    private static UserRepo userRepo;
    private static EmailTools emailTools;
    private static ConfirmationTokenService tokenService;
    private static EmailService emailService;
    private static Environment env;

    private static BCryptPasswordEncoder bCryptPasswordEncoder;

    private static UserRegistrationRequestDTO passed;
    private static UserRegistrationRequestDTO expected;

    @BeforeAll
    public static void setUp() {
        userService = new UserServiceImpl(userRepo, bCryptPasswordEncoder, tokenService, env);

        passed = new UserRegistrationRequestDTO(
                "Ahmed",
                "Ali",
                25,
                "userAhmed",
                "ahmed@test.com",
                "password",
                "urlToPicture"
        );

        expected = new UserRegistrationRequestDTO(
                passed.getFirstName(),
                passed.getLastName(),
                passed.getAge(),
                passed.getUsername(),
                passed.getEmail(),
                passed.getPassword(),
                passed.getProfilePicture()
        );
    }

    @Test
    @DisplayName("register(user)")
    public void register_user() {

    }

    @Test
    void confirmToken() {
    }
}