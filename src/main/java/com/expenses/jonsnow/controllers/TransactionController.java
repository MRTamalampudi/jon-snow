package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.config.URLConstants;
import com.expenses.jonsnow.dto.TransactionDTO;
import com.expenses.jonsnow.dto.request.TransactionRequest;
import com.expenses.jonsnow.mapper.TransactionMapper;
import com.expenses.jonsnow.model.Transaction;
import com.expenses.jonsnow.repository.TransactionRepo;
import com.expenses.jonsnow.service.TransactionService;
import com.expenses.jonsnow.specification.Builder.TransactionSpecificationBuilder;
import com.expenses.jonsnow.specification.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@RestController
@RequestMapping(value = URLConstants.TRANSACTIONS)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionController {

    private final TransactionService service;
    private final TransactionMapper mapper;
    private final TransactionRepo repo;



    @GetMapping
    public Page<TransactionDTO> index(
            List<SearchRequest> requests,
            Pageable pageable
    ) {
        Specification<Transaction> specification = new TransactionSpecificationBuilder(requests).build();
        return service.index(specification,pageable);
    }

    @GetMapping("/{id}")
    public TransactionDTO get(@PathVariable("id") Long entityId) {
        return mapper.mapEntityToDTO(repo.findById(entityId).orElse(new Transaction()));
    }

    @PostMapping
    public TransactionDTO create(@RequestBody TransactionRequest request) {
        Transaction transaction = mapper.map(request);
        return mapper.mapEntityToDTO(service.create(transaction));
    }

    @DeleteMapping
    public void delete(List<SearchRequest> requests) {
        List<Long> entityIds = requests.stream()
                .filter(searchRequest -> "id".equals(searchRequest.getKey()))
                .findFirst()
                .map(searchRequest -> searchRequest.getValues().stream()
                        .map(Long::parseLong)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
        service.deleteAllById(entityIds);
    }
}
