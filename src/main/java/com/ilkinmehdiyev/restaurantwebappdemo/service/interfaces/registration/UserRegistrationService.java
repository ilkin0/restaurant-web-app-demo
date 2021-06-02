package com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.registration;

import com.ilkinmehdiyev.restaurantwebappdemo.exception.EmailIsNotValidException;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.UserAlreadyExistsException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.dto.userregistration.UserRegistrationRequestDTO;

public interface UserRegistrationService {
    String register(UserRegistrationRequestDTO requestDTO) throws EmailIsNotValidException, UserAlreadyExistsException;
}
