package com.expenses.jonsnow.specification.Builder;

import com.expenses.jonsnow.model.SplitBillShare;
import com.expenses.jonsnow.specification.Builder.BaseSpecificationBuilder;
import com.expenses.jonsnow.specification.SearchRequest;
import com.expenses.jonsnow.specification.SplitBillShareSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SplitBillShareSpecificationBuilder extends BaseSpecificationBuilder<SplitBillShare> {
    public SplitBillShareSpecificationBuilder(List<SearchRequest> searchRequests) {
        super(searchRequests);
    }

    @Override
    protected Specification<SplitBillShare> createSpecification(SearchRequest searchRequest) {
        return new SplitBillShareSpecification(searchRequest);
    }
}
