package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.registration;

import com.ilkinmehdiyev.restaurantwebappdemo.exception.ConfirmationTokenNotfoundException;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.EmailIsNotValidException;
import com.ilkinmehdiyev.restaurantwebappdemo.exception.UserAlreadyExistsException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Token.ConfirmationToken;
import com.ilkinmehdiyev.restaurantwebappdemo.models.User.ApplicationUser;
import com.ilkinmehdiyev.restaurantwebappdemo.models.User.UserRole;
import com.ilkinmehdiyev.restaurantwebappdemo.models.dto.userregistration.UserRegistrationRequestDTO;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.confirmationtoken.ConfirmationTokenService;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.email.EmailService;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.registration.UserRegistrationService;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.user.UserService;
import com.ilkinmehdiyev.restaurantwebappdemo.util.EmailTools;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.ilkinmehdiyev.restaurantwebappdemo.util.EmailTools.buildEmail;

@Service
@AllArgsConstructor
@PropertySource("classpath:application.yml")
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserService userService;
    private final EmailTools emailTools;
    private final ConfirmationTokenService tokenService;
    private final EmailService emailService;
    private final Environment env;

    @Override
    public String register(UserRegistrationRequestDTO requestDTO) throws EmailIsNotValidException, UserAlreadyExistsException {
        boolean isValidEmail = emailTools.test(requestDTO.getEmail());
        if (!isValidEmail) {
            throw new EmailIsNotValidException(requestDTO.getEmail());
        }
        String token = userService.signUpUser(
                new ApplicationUser(
                        requestDTO.getFirstName(),
                        requestDTO.getLastName(),
                        requestDTO.getAge(),
                        requestDTO.getEmail(),
                        requestDTO.getUsername(),
                        requestDTO.getPassword(),
                        UserRole.USER,
                        requestDTO.getProfilePicture()
                )
        );
        emailService.send(requestDTO.getEmail(),
                buildEmail(requestDTO.getFirstName(), env.getProperty("email.confirmation-link") + token));

        return token;
    }

    @Override
    @Transactional
    public String confirmToken(String token) throws ConfirmationTokenNotfoundException {
        ConfirmationToken confirmationToken = tokenService.getConfirmationToken(token)
                .orElseThrow(ConfirmationTokenNotfoundException::new);

        if (confirmationToken.getConfirmedAt() != null)
            throw new IllegalStateException("Email already confirmed");

        LocalDateTime expiresAt = confirmationToken.getExpiresAt();

        if (expiresAt.isBefore(LocalDateTime.now()))
            throw new IllegalStateException("Token expired, please request new activation link");

        tokenService.setConfirmedAt(token);
        userService.enableUser(confirmationToken.getUser().getEmail());
        return HttpStatus.OK.toString();
    }
}
