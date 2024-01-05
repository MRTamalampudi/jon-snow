package com.expenses.jonsnow.repository;

import com.expenses.jonsnow.model.TransactionSummary;
import com.expenses.jonsnow.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionSummaryRepo extends BaseRepo<TransactionSummary,Long> {

    TransactionSummary findByUser(User user);

}
