package com.expenses.jonsnow.repository;

import com.expenses.jonsnow.model.Budget;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BudgerRepo extends BaseRepo<Budget,Long> {
}
