package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.registration;

import com.ilkinmehdiyev.restaurantwebappdemo.models.dto.userregistration.UserRegistrationRequestDTO;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.registration.UserRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Override
    public String register(UserRegistrationRequestDTO requestDTO) {
        return "service is working";
    }
}
