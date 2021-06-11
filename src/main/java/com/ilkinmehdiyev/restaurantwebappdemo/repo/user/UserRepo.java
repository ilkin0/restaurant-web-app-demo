package com.ilkinmehdiyev.restaurantwebappdemo.repo.user;

import com.ilkinmehdiyev.restaurantwebappdemo.models.User.ApplicationUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepo extends CrudRepository<ApplicationUser, Long> {

    Optional<ApplicationUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(
            "UPDATE ApplicationUser u " +
                    "SET u.isEnabled = TRUE, " +
                    "u.isNotLocked = TRUE, " +
                    "u.isNotExpired = TRUE, " +
                    "u.isNotCredentialsExpired = TRUE " +
                    "where u.email = :email"
    )
    int enableUser(String email);
}
