package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.user;

import com.ilkinmehdiyev.restaurantwebappdemo.exception.UserAlreadyExistsException;
import com.ilkinmehdiyev.restaurantwebappdemo.models.User.ApplicationUser;
import com.ilkinmehdiyev.restaurantwebappdemo.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.ilkinmehdiyev.restaurantwebappdemo.util.Constant.USER_NOT_FOUND_ERR_MESSAGE;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_ERR_MESSAGE, email)));
    }

    public String signUpUser(ApplicationUser user) throws UserAlreadyExistsException {
        boolean userExists = userRepo.findByEmail(user.getEmail())
                .isPresent();
        if (userExists)
            throw new UserAlreadyExistsException(user.getEmail());
        String encodedUserPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedUserPassword);

        userRepo.save(user);

        // TODO: Send Confirmation token
        return "";
    }
}
