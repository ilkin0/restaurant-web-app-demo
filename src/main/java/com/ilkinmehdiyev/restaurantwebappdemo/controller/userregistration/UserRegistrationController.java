package com.ilkinmehdiyev.restaurantwebappdemo.controller.userregistration;

import com.ilkinmehdiyev.restaurantwebappdemo.models.dto.userregistration.UserRegistrationRequestDTO;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.registration.UserRegistrationService;
import com.ilkinmehdiyev.restaurantwebappdemo.util.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ilkinmehdiyev.restaurantwebappdemo.util.Constant.USER_REGISTRATION;

@RestController
@RequestMapping(USER_REGISTRATION)
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    @PostMapping()
    public String register(@RequestBody UserRegistrationRequestDTO requestDTO) {
        return userRegistrationService.register(requestDTO);
    }
}
