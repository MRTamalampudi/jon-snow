package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.TransactionDTO;
import com.expenses.jonsnow.dto.request.TransactionRequest;
import com.expenses.jonsnow.model.Transaction;
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

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService extends BaseService<Transaction,TransactionDTO, TransactionRequest>{

    public TransactionService(TransactionRepo repo, TransactionMapper mapper) {
        super(repo,mapper);
    }
}
