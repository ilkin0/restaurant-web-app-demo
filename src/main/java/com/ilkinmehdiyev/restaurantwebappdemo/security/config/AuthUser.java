package com.ilkinmehdiyev.restaurantwebappdemo.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class AuthUser extends User {

    private long id;
    private String username;
    private String email;
    private String password;

    public AuthUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, UserInfo userInfo) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = userInfo.getId();
        this.username = userInfo.getUsername();
        this.email = userInfo.getEmail();
        this.password = userInfo.getPassword();
    }

    @Getter
    @Setter
    public static class UserInfo {
        private long id;
        private String username;
        private String email;
        private String password;
    }
}
