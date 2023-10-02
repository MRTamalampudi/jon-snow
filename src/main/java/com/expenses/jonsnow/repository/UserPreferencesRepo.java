package com.expenses.jonsnow.repository;

import com.expenses.jonsnow.model.UserPreferences;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserPreferencesRepo extends BaseRepo<UserPreferences,Long> {

}
