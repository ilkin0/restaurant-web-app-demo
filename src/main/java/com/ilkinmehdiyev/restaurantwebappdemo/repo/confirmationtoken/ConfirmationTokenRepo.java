package com.ilkinmehdiyev.restaurantwebappdemo.repo.confirmationtoken;

import com.ilkinmehdiyev.restaurantwebappdemo.models.Token.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenRepo extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findConfirmationTokenByToken(String token);

    @Transactional
    @Query(
            "UPDATE ConfirmationToken ctoken " +
                    "SET ctoken.confirmedAt = :confirmedAt " +
                    "WHERE ctoken.token = :token"
    )
    @Modifying
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
