package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.confirmationtoken;

import com.ilkinmehdiyev.restaurantwebappdemo.models.Token.ConfirmationToken;
import com.ilkinmehdiyev.restaurantwebappdemo.repo.confirmationtoken.ConfirmationTokenRepo;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.confirmationtoken.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepo confirmationTokenRepo;

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepo.save(token);
    }

    @Override
    public Optional<ConfirmationToken> getConfirmationToken(String token) {
        return confirmationTokenRepo.findConfirmationTokenByToken(token);
    }

    @Override
    public int setConfirmedAt(String token) {
        return confirmationTokenRepo.updateConfirmedAt(token, LocalDateTime.now());
    }
}
