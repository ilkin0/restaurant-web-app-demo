package com.ilkinmehdiyev.restaurantwebappdemo.repo;

import com.ilkinmehdiyev.restaurantwebappdemo.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepo extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
