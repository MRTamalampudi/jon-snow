package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.config.URLConstants;
import com.expenses.jonsnow.dto.TransactionSummaryDTO;
import com.expenses.jonsnow.model.AuditorAwareImpl;
import com.expenses.jonsnow.model.User;
import com.expenses.jonsnow.service.TransactionSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = URLConstants.TRANSACTION_SUMMARY)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionSummaryController {

    private final TransactionSummaryService service;

    @GetMapping
    public TransactionSummaryDTO get(){
        return service.get();
    }
}
