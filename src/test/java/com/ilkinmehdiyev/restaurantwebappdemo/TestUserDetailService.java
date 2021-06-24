package com.ilkinmehdiyev.restaurantwebappdemo;

import com.ilkinmehdiyev.restaurantwebappdemo.models.User.ApplicationUser;
import com.ilkinmehdiyev.restaurantwebappdemo.models.User.UserRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class TestUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return buildUser(s);
    }


    public ApplicationUser buildUser(String role) {
        UserRole userRole = UserRole.getByName(role);

        ApplicationUser user = new ApplicationUser();
        user.setId(1);
        user.setPassword("12345");
        user.setEmail("testEmail");
        user.setUserRole(userRole);
        user.setAge(20);
        user.setEnabled(false);
        user.setUsername("username");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setNotCredentialsExpired(false);
        user.setNotExpired(false);
        user.setEnabled(false);
        user.setNotLocked(false);
        user.setProfilePicture("picture_url");
        return user;
    }
}
