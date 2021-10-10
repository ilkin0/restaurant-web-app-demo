package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.confirmationtoken;

import com.ilkinmehdiyev.restaurantwebappdemo.dto.token.ConfirmationTokenDTO;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Token.ConfirmationToken;
import com.ilkinmehdiyev.restaurantwebappdemo.repo.confirmationtoken.ConfirmationTokenRepo;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.confirmationtoken.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepo confirmationTokenRepo;
    private final ModelMapper mapper;

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepo.save(token);
    }

    @Override
    public Optional<ConfirmationTokenDTO> getConfirmationToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepo.findConfirmationTokenByToken(token)
                .orElseThrow(() -> new RuntimeException("Error")
//                        TODO custom Exception for Confirmation class
                );
        return Optional.of(mapper.map(confirmationToken, ConfirmationTokenDTO.class));
    }

    @Override
    public int setConfirmedAt(String token) {
        return confirmationTokenRepo.updateConfirmedAt(token, LocalDateTime.now());
    }
}
