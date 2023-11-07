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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionService implements CRUDService<Transaction,TransactionDTO> {

    private final TransactionRepo transactions;
    private final TransactionMapper mapper;
    private final TransactionRepo repo;

    @Override
    public Page<TransactionDTO> index(Specification<Transaction> specification,Pageable pageable){
       Page<Transaction> transactionPage = transactions.findAll(specification,pageable);
       List<TransactionDTO> transactionDTOS = mapper.map(transactionPage.getContent());
       return new PageImpl<>(
               transactionDTOS,
               pageable,
               transactionPage.getTotalElements()
       );
    }

    @Override
    public Optional<Transaction> findById(Long entityId) {
        return repo.findById(entityId);
    }

    @Override
    public void deleteById(Long enityId) {
        repo.deleteById(enityId);
    }

    @Override
    public Transaction create(Transaction transaction) {
        return repo.save(transaction);
    }

    @Override
    public List<Transaction> findAll(Specification<Transaction> specification) {
        return repo.findAll(specification);
    }
}
