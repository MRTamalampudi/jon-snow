package com.expenses.jonsnow.specification.Builder;

import com.expenses.jonsnow.model.Transaction;
import com.expenses.jonsnow.specification.SearchRequest;
import com.expenses.jonsnow.specification.TransactionSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TransactionSpecificationBuilder extends BaseSpecificationBuilder<Transaction>{

    public TransactionSpecificationBuilder(List<SearchRequest> searchRequests) {
        super(searchRequests);
    }



    @Override
    protected Specification<Transaction> createSpecification(SearchRequest searchRequest) {
        return new TransactionSpecification(searchRequest);
    }
}
