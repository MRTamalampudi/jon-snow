package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.TransactionDTO;
import com.expenses.jonsnow.model.Transaction;
import com.expenses.jonsnow.repository.TransactionRepo;
import lombok.RequiredArgsConstructor;
import com.expenses.jonsnow.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionService {
    private final TransactionRepo transactions;
    private final TransactionMapper mapper;

    public Page<TransactionDTO> index(Pageable pageable){
       Page<Transaction> transactionPage = transactions.findAll(pageable);
       List<TransactionDTO> transactionDTOS = mapper.map(transactionPage.getContent());
       return new PageImpl<>(transactionDTOS,pageable,transactionPage.getTotalElements());
    }

    public Transaction save(Transaction transaction){
        return transactions.save(transaction);
    }
}
