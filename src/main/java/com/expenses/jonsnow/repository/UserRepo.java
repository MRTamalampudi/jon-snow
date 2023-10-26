package com.expenses.jonsnow.repository;

import com.expenses.jonsnow.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends BaseRepo<User,Long> {
    Optional<User> findByEmail(String email);
}
