package com.expenses.jonsnow.specification.Builder;

import com.expenses.jonsnow.model.Transaction;
import com.expenses.jonsnow.specification.SearchRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class TransactionSpecificationBuilder extends BaseSpecificationBuilder<Transaction>{

    public TransactionSpecificationBuilder(List<SearchRequest> searchRequests) {
        super(searchRequests);
    }

    @Override
    public Specification<Transaction> build() {
        return null;
    }

    @Override
    public Specification<Transaction> createSpecification(SearchRequest searchRequest) {
        return null;
    }
}
