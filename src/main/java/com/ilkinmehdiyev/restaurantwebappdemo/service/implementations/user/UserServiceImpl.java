package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.user;

import com.ilkinmehdiyev.restaurantwebappdemo.exception.UserAlreadyExistsException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.Token.ConfirmationToken;
import com.ilkinmehdiyev.restaurantwebappdemo.models.User.ApplicationUser;
import com.ilkinmehdiyev.restaurantwebappdemo.repo.user.UserRepo;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.confirmationtoken.ConfirmationTokenService;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.ilkinmehdiyev.restaurantwebappdemo.util.Constant.USER_NOT_FOUND_ERR_MESSAGE;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:application.yml")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService tokenService;
    private final Environment env;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_ERR_MESSAGE, email)));
    }

    public String signUpUser(ApplicationUser user) throws UserAlreadyExistsException {
        boolean userExists = userRepo.findByEmail(user.getEmail())
                .isPresent();

        // TODO check of attributes are the same and
        // TODO if email not confirmed send confirmation email.
        if (userExists)
            throw new UserAlreadyExistsException(user.getEmail());

        String encodedUserPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedUserPassword);

        userRepo.save(user);

        //Generate and save Confirmation token
        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now().plusMinutes(Long.parseLong(env.getProperty("confirmationtoken.expireDuration"))),
                user
        );

        tokenService.saveConfirmationToken(confirmationToken);

        // TODO: Send Confirmation token via email in order ot activate user
        return token;
    }

    public int enableUser(String email) {
        return userRepo.enableUser(email);
    }
}
