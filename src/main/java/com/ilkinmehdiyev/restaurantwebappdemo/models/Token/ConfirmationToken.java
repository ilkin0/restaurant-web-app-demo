package com.ilkinmehdiyev.restaurantwebappdemo.models.Token;

import com.ilkinmehdiyev.restaurantwebappdemo.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
}
