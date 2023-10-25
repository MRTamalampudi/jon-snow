package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.TransactionDTO;
import com.expenses.jonsnow.model.Transaction;
import com.expenses.jonsnow.repository.TransactionRepo;
import com.expenses.jonsnow.specification.BaseSpecification;
import lombok.RequiredArgsConstructor;
import com.expenses.jonsnow.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionService implements BaseService<Transaction>{
    private final TransactionRepo transactions;
    private final TransactionMapper mapper;

    public Page<TransactionDTO> index(Specification<Transaction> specification,Pageable pageable){
       Page<Transaction> transactionPage = transactions.findAll(specification,pageable);
       List<TransactionDTO> transactionDTOS = mapper.map(transactionPage.getContent());
       return new PageImpl<>(
               transactionDTOS,
               pageable,
               transactionPage.getTotalElements()
       );
    }

    public Transaction save(Transaction transaction){
        return transactions.save(transaction);
    }

    @Override
    public Page<Transaction> index(BaseSpecification<Transaction> baseSpecification, Pageable pageable) {
        return null;
    }
}
