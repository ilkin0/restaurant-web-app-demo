package com.ilkinmehdiyev.restaurantwebappdemo.dto.token;

import com.ilkinmehdiyev.restaurantwebappdemo.dto.BaseDTO;
import com.ilkinmehdiyev.restaurantwebappdemo.dto.user.ApplicationUserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class ConfirmationTokenDTO extends BaseDTO {

    private String token;

    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    private ApplicationUserDTO user;
}

