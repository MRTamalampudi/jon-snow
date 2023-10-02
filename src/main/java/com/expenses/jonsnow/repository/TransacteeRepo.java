package com.expenses.jonsnow.repository;

import com.expenses.jonsnow.model.Transactee;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TransacteeRepo extends BaseRepo<Transactee,Long> {
}
