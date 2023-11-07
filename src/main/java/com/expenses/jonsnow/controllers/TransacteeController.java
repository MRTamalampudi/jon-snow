package com.expenses.jonsnow.controllers;


import com.expenses.jonsnow.config.URLConstants;
import com.expenses.jonsnow.dto.TransacteeDTO;
import com.expenses.jonsnow.dto.request.TransacteeRequest;
import com.expenses.jonsnow.mapper.TransacteeMapper;
import com.expenses.jonsnow.model.Transactee;
import com.expenses.jonsnow.service.TransacteeService;
import com.expenses.jonsnow.specification.Builder.TransacteeSpecificationBuilder;
import com.expenses.jonsnow.specification.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = URLConstants.TRANSACTEE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransacteeController
        implements CRUDController<Transactee,TransacteeDTO, TransacteeRequest> {

    private final TransacteeService service;
    private final TransacteeMapper mapper;
    @Override
    public Page<TransacteeDTO> index(List<SearchRequest> requests, Pageable pageable) {
        Specification<Transactee> specification = new TransacteeSpecificationBuilder(requests).build();
        return service.index(specification,pageable);
    }

    @Override
    public TransacteeDTO get(Long entityId) {
        return null;
    }

    @Override
    public TransacteeDTO create(TransacteeRequest transacteeRequest) {
        return mapper.map(
                service.create(
                        mapper.map(transacteeRequest)
                )
        );
    }

    @Override
    public void delete(Long entityId) {

    }
}
