package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.config.URLConstants;
import com.expenses.jonsnow.dto.TransactionDTO;
import com.expenses.jonsnow.dto.request.TransactionRequest;
import com.expenses.jonsnow.mapper.TransactionMapper;
import com.expenses.jonsnow.model.Transaction;
import com.expenses.jonsnow.model.enums.TransactionType;
import com.expenses.jonsnow.repository.TransactionRepo;
import com.expenses.jonsnow.service.TransactionService;
import com.expenses.jonsnow.specification.Builder.BaseSpecificationBuilder;
import com.expenses.jonsnow.specification.Builder.TransactionSpecificationBuilder;
import com.expenses.jonsnow.specification.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = URLConstants.TRANSACTIONS)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionController implements
        BaseController<TransactionDTO,TransactionRequest>{

    private final TransactionService service;
    private final TransactionMapper mapper;
    private final TransactionRepo repo;


    @Override
    public List<TransactionDTO> index(List<SearchRequest> requests, Pageable pageable) {
        Specification<Transaction> specification = new TransactionSpecificationBuilder(requests).build();
        return service.index(specification,pageable).getContent();
    }

    @Override
    public TransactionDTO get(Long entityId) {
        return mapper.map(repo.findById(entityId).orElse(new Transaction()));
    }

    @Override
    public TransactionRequest create(TransactionRequest request) {
        return request;
    }

    @Override
    public void delete(Long transactionId) {
        repo.deleteById(transactionId);
    }
}
