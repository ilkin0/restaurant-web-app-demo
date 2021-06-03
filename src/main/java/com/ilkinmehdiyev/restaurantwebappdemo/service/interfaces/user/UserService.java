package com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.user;

import com.ilkinmehdiyev.restaurantwebappdemo.exception.UserAlreadyExistsException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.User.ApplicationUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails loadUserByUsername(String email);

    String signUpUser(ApplicationUser user) throws UserAlreadyExistsException;

    int enableUser(String email);
}
