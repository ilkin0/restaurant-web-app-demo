package com.ilkinmehdiyev.restaurantwebappdemo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

@ResponseStatus(value = CONFLICT)
public class EntityCouldNotBeDeletedException extends Exception {

    public EntityCouldNotBeDeletedException(Class entityClass, long id) {
        super(entityClass.getSimpleName() + " with ID:" + id + " could not be deleted");
    }

    public EntityCouldNotBeDeletedException(Class entityClass, long id, Exception e) {
        super(entityClass.getSimpleName() + " with ID:" + id + " could not be deleted");
        setStackTrace(e.getStackTrace());
    }


}
