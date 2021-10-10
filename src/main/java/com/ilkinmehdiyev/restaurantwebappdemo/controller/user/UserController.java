package com.ilkinmehdiyev.restaurantwebappdemo.controller.user;

import com.ilkinmehdiyev.restaurantwebappdemo.exception.ConfirmationTokenNotfoundException;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.UserAlreadyExistsException;
import com.ilkinmehdiyev.restaurantwebappdemo.dto.userregistration.UserRegistrationRequestDTO;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.registration.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.ilkinmehdiyev.restaurantwebappdemo.util.Constant.USER_REGISTRATION;

@RestController
@RequestMapping(USER_REGISTRATION)
@RequiredArgsConstructor
public class UserController {

    private final UserRegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody UserRegistrationRequestDTO requestDTO) throws UserAlreadyExistsException {
        return registrationService.register(requestDTO);
    }

    @GetMapping(path = "confirm")
    public String confirmUser(@RequestParam("token") String token) throws ConfirmationTokenNotfoundException {
        return registrationService.confirmToken(token);
    }
}
