package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.TransactionSummaryDTO;
import com.expenses.jonsnow.mapper.BaseMapper;
import com.expenses.jonsnow.mapper.TransactionSummaryMapper;
import com.expenses.jonsnow.model.AuditorAwareImpl;
import com.expenses.jonsnow.model.TransactionSummary;
import com.expenses.jonsnow.model.User;
import com.expenses.jonsnow.repository.BaseRepo;
import com.expenses.jonsnow.repository.TransactionSummaryRepo;
import org.springframework.stereotype.Service;

@Service
public class TransactionSummaryService extends BaseService<TransactionSummary, TransactionSummaryDTO,TransactionSummaryDTO> {

    private final TransactionSummaryRepo repo;
    private final TransactionSummaryMapper mapper;
    public TransactionSummaryService(TransactionSummaryRepo repo,TransactionSummaryMapper mapper) {
        super(repo, mapper);
        this.repo = repo;
        this.mapper = mapper;
    }

    public TransactionSummaryDTO get(){
        AuditorAwareImpl auditorAware = new AuditorAwareImpl();
        User user = auditorAware.getCurrentAuditor().orElse(new User());
        TransactionSummary summary = this.repo.findByUser(user);
        return this.mapper.mapEntityToDTO(summary);
    }
}
