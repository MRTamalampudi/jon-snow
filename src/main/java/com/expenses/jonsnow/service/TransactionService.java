package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.TransactionDTO;
import com.expenses.jonsnow.dto.request.TransactionRequest;
import com.expenses.jonsnow.exceptions.NoSuchEntityException;
import com.expenses.jonsnow.model.Transaction;
import com.expenses.jonsnow.model.TransactionSummary;
import com.expenses.jonsnow.model.enums.TransactionType;
import com.expenses.jonsnow.pojo.TransactionSumm;
import com.expenses.jonsnow.repository.BaseRepo;
import com.expenses.jonsnow.repository.TransactionRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.expenses.jonsnow.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@Transactional
public class TransactionService extends BaseService<Transaction,TransactionDTO, TransactionRequest>{

    private final TransactionSummaryService transactionSummaryService;
    private final TransactionRepo repo;

    public TransactionService(TransactionRepo repo, TransactionMapper mapper, TransactionSummaryService transactionSummaryService) {
        super(repo,mapper);
        this.transactionSummaryService = transactionSummaryService;
        this.repo = repo;
    }

    @Override
    public Transaction create(Transaction transaction) {
        transaction = super.create(transaction);
        updateTransactionSummary(transaction);
        return transaction;
    }

    private void updateTransactionSummary(Transaction transaction) {
        TransactionSummary summary = transactionSummaryService.getSummary();
        Long amount = repo.getAmountSum(transaction.getUser().getId(),transaction.getType().toString());
        switch (transaction.getType()) {
            case CASH_IN -> summary.setCashIn(amount);
            case CASH_OUT -> summary.setCashOut(amount);
            case LENT -> summary.setLent(amount);
            case OWE -> summary.setOwe(amount);
        }
        transactionSummaryService.create(summary);
    }

    @Override
    public Optional<Transaction> update(TransactionRequest transactionRequest) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchEntityException {
        Optional<Transaction> transaction= super.update(transactionRequest);
        transaction.ifPresent(this::updateTransactionSummary);
        return transaction;
    }

    @Override
    public void deleteAllById(List<Long> entityIds) {
        super.deleteAllById(entityIds);
        updateTransactionSummary();
    }

    private void updateTransactionSummary() {
        List<TransactionSumm> transactionSumms = repo.sumAmount(UserContext.getUser().getId());
        TransactionSummary summary = transactionSummaryService.getSummary();
        transactionSumms.forEach(transactionSumm -> {
            switch (transactionSumm.getType()){
                case CASH_IN -> summary.setCashIn(transactionSumm.getAmount());
                case CASH_OUT -> summary.setCashOut(transactionSumm.getAmount());
                case LENT -> summary.setLent(transactionSumm.getAmount());
                case OWE -> summary.setOwe(transactionSumm.getAmount());
            }
        });
        transactionSummaryService.create(summary);
    }
}
