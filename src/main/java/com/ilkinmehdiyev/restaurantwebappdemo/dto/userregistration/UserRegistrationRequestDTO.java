package com.ilkinmehdiyev.restaurantwebappdemo.dto.userregistration;

import lombok.*;

@Data
@AllArgsConstructor
public class UserRegistrationRequestDTO {
    private String firstName;
    private String lastName;
    private int age;
    private String username;
    private String email;
    private String password;
    private String profilePicture;
}
