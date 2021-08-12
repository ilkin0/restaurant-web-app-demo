package com.ilkinmehdiyev.restaurantwebappdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String message) {
        super("Entity not found!");
    }

    public EntityNotFoundException(Class entityClass, long id) {
        super(entityClass.getSimpleName() + " not found with id: " + id);
    }
}
