package com.expenses.jonsnow.specification.Builder;

import com.expenses.jonsnow.model.Transactee;
import com.expenses.jonsnow.specification.SearchRequest;
import com.expenses.jonsnow.specification.TransacteeSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class TransacteeSpecificationBuilder extends BaseSpecificationBuilder<Transactee> {
    public TransacteeSpecificationBuilder(List<SearchRequest> searchRequests) {
        super(searchRequests);
    }

    @Override
    protected Specification<Transactee> createSpecification(SearchRequest searchRequest) {
        return new TransacteeSpecification(searchRequest);
    }
}
