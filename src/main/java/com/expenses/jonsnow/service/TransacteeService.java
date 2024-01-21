package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.TransacteeDTO;
import com.expenses.jonsnow.dto.request.TransacteeRequest;
import com.expenses.jonsnow.mapper.BaseMapper;
import com.expenses.jonsnow.mapper.TransacteeMapper;
import com.expenses.jonsnow.model.Transactee;
import com.expenses.jonsnow.repository.BaseRepo;
import com.expenses.jonsnow.repository.TransacteeRepo;
import com.expenses.jonsnow.repository.TransactionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransacteeService extends BaseService<Transactee, TransacteeDTO, TransacteeRequest> {


    public TransacteeService(TransacteeRepo repo, TransacteeMapper mapper) {
        super(repo, mapper);
    }
}
