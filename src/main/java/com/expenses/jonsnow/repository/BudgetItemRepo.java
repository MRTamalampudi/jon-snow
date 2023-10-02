package com.expenses.jonsnow.repository;

import com.expenses.jonsnow.model.BudgetItem;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BudgetItemRepo extends BaseRepo<BudgetItem,Long> {
}
