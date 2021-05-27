package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.user;

import com.ilkinmehdiyev.restaurantwebappdemo.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.ilkinmehdiyev.restaurantwebappdemo.util.Constant.USER_NOT_FOUND_ERR_MESSAGE;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return (UserDetails) userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_ERR_MESSAGE, email)));
    }
}
