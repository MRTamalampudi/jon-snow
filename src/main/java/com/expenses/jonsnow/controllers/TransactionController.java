package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.config.URLConstants;
import com.expenses.jonsnow.dto.TransactionDTO;
import com.expenses.jonsnow.dto.request.TransactionRequest;
import com.expenses.jonsnow.exceptions.NoSuchEntityException;
import com.expenses.jonsnow.mapper.TransactionMapper;
import com.expenses.jonsnow.model.AuditorAwareImpl;
import com.expenses.jonsnow.model.Transaction;
import com.expenses.jonsnow.repository.TransactionRepo;
import com.expenses.jonsnow.service.TransactionService;
import com.expenses.jonsnow.specification.Builder.TransactionSpecificationBuilder;
import com.expenses.jonsnow.specification.Operator;
import com.expenses.jonsnow.specification.SearchRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = URLConstants.TRANSACTIONS)
@Log4j2
public class TransactionController extends BaseController<Transaction,TransactionDTO,TransactionRequest> {

    private final TransactionService service;
    private final TransactionMapper mapper;
    private final TransactionRepo repo;
    private final static TransactionSpecificationBuilder builder =
            new TransactionSpecificationBuilder(null);

    public TransactionController(TransactionService service, TransactionMapper mapper,TransactionRepo repo) {
        super(service, mapper, builder);
        this.service = service;
        this.mapper = mapper;
        this.repo = repo;
    }



    @GetMapping
    public Page<TransactionDTO> index(
            List<SearchRequest> requests,
            Pageable pageable
    ) {
        SearchRequest searchRequest = new SearchRequest(
                "user",
                Operator.EQUALITY,
                new AuditorAwareImpl().getCurrentAuditor().get().getId().toString(),
                null
        );
        requests.add(searchRequest);
        return super.index(requests,pageable);
    }

    @GetMapping("/{id}")
    public TransactionDTO get(@PathVariable("id") Long entityId) throws NoSuchEntityException {
        return mapper.mapEntityToDTO(service.findById(entityId));
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
