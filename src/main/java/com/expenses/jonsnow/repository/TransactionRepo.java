package com.expenses.jonsnow.repository;

import com.expenses.jonsnow.model.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TransactionRepo extends BaseRepo<Transaction,Long> {
    Boolean existsByAmount(Long amount);
}
