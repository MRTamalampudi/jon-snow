package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.config.URLConstants;
import com.expenses.jonsnow.dto.TransactionDTO;
import com.expenses.jonsnow.dto.request.TransactionRequest;
import com.expenses.jonsnow.mapper.TransactionMapper;
import com.expenses.jonsnow.model.Transaction;
import com.expenses.jonsnow.model.enums.TransactionType;
import com.expenses.jonsnow.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping(value = URLConstants.TRANSACTIONS)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionController {

    private final TransactionService service;
    private final TransactionMapper mapper;
    @GetMapping
    public Page<TransactionDTO> index(Pageable pageable){
        return service.index(pageable);
    }

    @PostMapping()
    public void create(@RequestBody TransactionRequest request){

    }
}
