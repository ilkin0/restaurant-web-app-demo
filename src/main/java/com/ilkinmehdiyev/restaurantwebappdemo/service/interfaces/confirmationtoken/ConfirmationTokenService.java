package com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.confirmationtoken;

import com.ilkinmehdiyev.restaurantwebappdemo.models.Token.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {
    void saveConfirmationToken(ConfirmationToken token);

    Optional<ConfirmationToken> getConfirmationToken(String token);

    int setConfirmedAt(String token);
}
