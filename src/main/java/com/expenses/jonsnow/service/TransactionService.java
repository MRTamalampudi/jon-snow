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
    
    public TransactionService(TransactionRepo repo, TransactionMapper mapper, TransactionSummaryService transactionSummaryService) {
        super(repo,mapper);
    }

    @Override
    public Transaction create(Transaction transaction) {
        return super.create(transaction);
    }

    @Override
    public Optional<Transaction> update(TransactionRequest transactionRequest) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchEntityException {
        return super.update(transactionRequest);
    }

    @Override
    public void deleteAllById(List<Long> entityIds) {
        super.deleteAllById(entityIds);
    }
}
