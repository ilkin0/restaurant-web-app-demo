package com.ilkinmehdiyev.restaurantwebappdemo.models.User;

import com.ilkinmehdiyev.restaurantwebappdemo.models.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
public class ApplicationUser extends BaseEntity implements UserDetails, CredentialsContainer {

    private static final long serialVersionUID = 4L;

    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String username;
    private String password;
    private String profilePicture;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private boolean isLocked = false;
    private boolean isEnabled = false;
    private boolean isExpired = false;
    private boolean isCredentialsExpired = false;

    public ApplicationUser(String firstName, String lastName, int age, String email, String username, String password,
                           UserRole userRole, String profilePicture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.profilePicture = profilePicture;
    }


//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<Food> favorites;

    //    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<Food> allergic;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public void eraseCredentials() {

    }

}
