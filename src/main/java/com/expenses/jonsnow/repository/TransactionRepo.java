package com.expenses.jonsnow.repository;

import com.expenses.jonsnow.model.Transaction;
import com.expenses.jonsnow.pojo.TransactionSumm;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface TransactionRepo extends BaseRepo<Transaction,Long> {

    @Query(value = "select sum(amount) from `transactions` where `user_id` = :userId and `transaction_type` = :transactionType group by `transaction_type`",nativeQuery = true)
    Long findAmountSumByUserIdAndTransactionType(Long userId, String transactionType);

    @Query(value = "select sum(amount) as amount,transaction_type as type " +
            "from `transactions` " +
            "where `user_id` = :userId" +
            " group by `transaction_type`",
            nativeQuery = true)
    List<TransactionSumm> sumAmount(Long userId);
}
