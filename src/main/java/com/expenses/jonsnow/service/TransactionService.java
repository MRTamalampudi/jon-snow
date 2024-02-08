package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.TransactionDTO;
import com.expenses.jonsnow.dto.request.TransactionRequest;
import com.expenses.jonsnow.model.Transaction;
import com.expenses.jonsnow.model.TransactionSummary;
import com.expenses.jonsnow.repository.BaseRepo;
import com.expenses.jonsnow.repository.TransactionRepo;
import lombok.RequiredArgsConstructor;
import com.expenses.jonsnow.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService extends BaseService<Transaction,TransactionDTO, TransactionRequest>{

    private final TransactionSummaryService transactionSummaryService;

    public TransactionService(TransactionRepo repo, TransactionMapper mapper, TransactionSummaryService transactionSummaryService) {
        super(repo,mapper);
        this.transactionSummaryService = transactionSummaryService;
    }

    @Override
    public Transaction create(Transaction transaction) {
        transaction = super.create(transaction);
        TransactionSummary summary = transactionSummaryService.getSummary();
        switch (transaction.getType()){
            case CASH_IN -> summary.setCashIn(transaction.getAmount() + summary.getCashIn());
            case CASH_OUT -> summary.setCashOut(transaction.getAmount() + summary.getCashOut());
            case LENT -> summary.setLent(transaction.getAmount() + summary.getLent());
            case OWE -> summary.setOwe(transaction.getAmount() + summary.getOwe());
        }
        transactionSummaryService.update(summary);
        return transaction;
    }
}
