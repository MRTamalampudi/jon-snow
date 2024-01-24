package com.expenses.jonsnow.specification.Builder;

import com.expenses.jonsnow.model.SplitBill;
import com.expenses.jonsnow.specification.SearchRequest;
import com.expenses.jonsnow.specification.SplitBillSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SplitBillSpecificationBuilder extends BaseSpecificationBuilder<SplitBill>{
    public SplitBillSpecificationBuilder(List<SearchRequest> searchRequests) {
        super(searchRequests);
    }

    @Override
    protected Specification<SplitBill> createSpecification(SearchRequest searchRequest) {
        return new SplitBillSpecification(searchRequest);
    }
}
