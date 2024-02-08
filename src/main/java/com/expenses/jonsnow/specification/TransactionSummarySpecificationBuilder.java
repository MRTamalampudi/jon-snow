package com.expenses.jonsnow.specification;

import com.expenses.jonsnow.model.TransactionSummary;
import com.expenses.jonsnow.specification.Builder.BaseSpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class TransactionSummarySpecificationBuilder extends BaseSpecificationBuilder<TransactionSummary> {

    public TransactionSummarySpecificationBuilder(List<SearchRequest> searchRequests) {
        super(searchRequests);
    }

    @Override
    protected Specification<TransactionSummary> createSpecification(SearchRequest searchRequest) {
        return new TransactionSummarySpecification(searchRequest);
    }
}
