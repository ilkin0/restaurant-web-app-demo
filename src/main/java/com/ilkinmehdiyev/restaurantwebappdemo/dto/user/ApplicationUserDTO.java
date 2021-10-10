package com.ilkinmehdiyev.restaurantwebappdemo.dto.user;

import com.ilkinmehdiyev.restaurantwebappdemo.dto.BaseDTO;
import com.ilkinmehdiyev.restaurantwebappdemo.models.User.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApplicationUserDTO extends BaseDTO {

    private String firstName;

    private String lastName;

    private int age;

    private String email;

    private String username;

    private String password;

    private String profilePicture;

    private UserRole userRole;

    private boolean isNotLocked;

    private boolean isEnabled;

    private boolean isNotExpired;

    private boolean isNotCredentialsExpired;
}
