package com.expenses.jonsnow.repository;

import com.expenses.jonsnow.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends BaseRepo<User,Long> {
}
