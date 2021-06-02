package com.ilkinmehdiyev.restaurantwebappdemo.models.dto.userregistration;

import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequestDTO {
    private String firstName;
    private String lastName;
    private int age;
    private String username;
    private String email;
    private String password;
    private String profilePicture;
}
