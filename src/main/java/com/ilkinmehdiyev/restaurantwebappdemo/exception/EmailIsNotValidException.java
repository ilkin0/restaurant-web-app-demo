package com.ilkinmehdiyev.restaurantwebappdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class EmailIsNotValidException extends RuntimeException {
    public EmailIsNotValidException(String email) {
        super(String.format("Email %s is not valid", email));
    }
}
