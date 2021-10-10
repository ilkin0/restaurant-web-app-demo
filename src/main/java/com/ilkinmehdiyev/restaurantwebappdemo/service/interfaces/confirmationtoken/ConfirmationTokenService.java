package com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.confirmationtoken;

import com.ilkinmehdiyev.restaurantwebappdemo.dto.token.ConfirmationTokenDTO;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Token.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {
    void saveConfirmationToken(ConfirmationToken token);

    Optional<ConfirmationTokenDTO> getConfirmationToken(String token);

    int setConfirmedAt(String token);
}
