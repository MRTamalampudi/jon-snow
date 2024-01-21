package com.expenses.jonsnow.controllers;


import com.expenses.jonsnow.config.URLConstants;
import com.expenses.jonsnow.dto.TransacteeDTO;
import com.expenses.jonsnow.dto.request.TransacteeRequest;
import com.expenses.jonsnow.mapper.BaseMapper;
import com.expenses.jonsnow.mapper.TransacteeMapper;
import com.expenses.jonsnow.model.Transactee;
import com.expenses.jonsnow.service.BaseService;
import com.expenses.jonsnow.service.TransacteeService;
import com.expenses.jonsnow.specification.Builder.BaseSpecificationBuilder;
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
public class TransacteeController
        extends BaseController<Transactee,TransacteeDTO, TransacteeRequest> {

    private final static TransacteeSpecificationBuilder builder =
            new TransacteeSpecificationBuilder(null);

    public TransacteeController(TransacteeService service,TransacteeMapper mapper) {
        super(service, mapper, builder);
    }
}
