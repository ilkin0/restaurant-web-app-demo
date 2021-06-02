package com.ilkinmehdiyev.restaurantwebappdemo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String email) {
        super(String.format("User with %s email is already exists.", email));
    }
}
