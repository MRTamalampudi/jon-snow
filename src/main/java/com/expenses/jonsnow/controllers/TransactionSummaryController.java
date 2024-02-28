package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.config.URLConstants;
import com.expenses.jonsnow.dto.TransactionSummaryDTO;
import com.expenses.jonsnow.mapper.TransactionSummaryMapper;
import com.expenses.jonsnow.service.JWTService;
import com.expenses.jonsnow.service.TransactionSummaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = URLConstants.TRANSACTION_SUMMARY)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class TransactionSummaryController {

    private final TransactionSummaryService service;
    private final TransactionSummaryMapper mapper;
    private final JWTService jwtService;

    @GetMapping
    public TransactionSummaryDTO get(){
        return mapper.mapEntityToDTO(service.getSummary());
    }
}
