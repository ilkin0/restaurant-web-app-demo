package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.registration;

import com.ilkinmehdiyev.restaurantwebappdemo.exception.EmailIsNotValidException;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.UserAlreadyExistsException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.User.ApplicationUser;
import com.ilkinmehdiyev.restaurantwebappdemo.models.User.UserRole;
import com.ilkinmehdiyev.restaurantwebappdemo.models.dto.userregistration.UserRegistrationRequestDTO;
import com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.user.UserService;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.registration.UserRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;

    @Override
    public String register(UserRegistrationRequestDTO requestDTO) throws EmailIsNotValidException, UserAlreadyExistsException {
        boolean isValidEmail = emailValidator.test(requestDTO.getEmail());
        if (!isValidEmail) {
            throw new EmailIsNotValidException(requestDTO.getEmail());
        }
        return userService.signUpUser(
                new ApplicationUser(
                        requestDTO.getFirstName(),
                        requestDTO.getLastName(),
                        requestDTO.getAge(),
                        requestDTO.getEmail(),
                        requestDTO.getUsername(),
                        requestDTO.getPassword(),
                        UserRole.USER,
                        requestDTO.getProfilePicture()
                )
        );
    }
}
