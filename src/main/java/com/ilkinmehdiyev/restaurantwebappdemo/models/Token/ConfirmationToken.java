package com.ilkinmehdiyev.restaurantwebappdemo.models.Token;

import com.ilkinmehdiyev.restaurantwebappdemo.models.BaseEntity;
import com.ilkinmehdiyev.restaurantwebappdemo.models.User.ApplicationUser;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "CONFIRMATION_TOKEN")
@NoArgsConstructor
public class ConfirmationToken extends BaseEntity {

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private ApplicationUser user;

    public ConfirmationToken(String token, LocalDateTime expiresAt, ApplicationUser user) {
        this.token = token;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}
