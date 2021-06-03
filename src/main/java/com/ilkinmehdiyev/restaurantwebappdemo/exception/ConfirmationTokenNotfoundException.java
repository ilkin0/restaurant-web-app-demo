package com.ilkinmehdiyev.restaurantwebappdemo.exception;

public class ConfirmationTokenNotfoundException extends Exception {
    public ConfirmationTokenNotfoundException() {
        super("Token not found");
    }
}
