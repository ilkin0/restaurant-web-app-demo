package com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.registration;

import com.ilkinmehdiyev.restaurantwebappdemo.exception.ConfirmationTokenNotfoundException;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EmailIsNotValidException;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.UserAlreadyExistsException;
import com.ilkinmehdiyev.restaurantwebappdemo.dto.userregistration.UserRegistrationRequestDTO;

public interface UserRegistrationService {
    String register(UserRegistrationRequestDTO requestDTO) throws EmailIsNotValidException, UserAlreadyExistsException;

    String confirmToken(String token) throws ConfirmationTokenNotfoundException;
}
