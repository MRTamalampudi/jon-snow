package com.expenses.jonsnow.specification.Builder;

import com.expenses.jonsnow.model.SplitBillGroup;
import com.expenses.jonsnow.model.SplitBillGroupMember;
import com.expenses.jonsnow.specification.SearchRequest;
import com.expenses.jonsnow.specification.SplitBillGroupSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SplitBillGroupSpecificationBuilder extends BaseSpecificationBuilder<SplitBillGroup> {
    public SplitBillGroupSpecificationBuilder(List<SearchRequest> searchRequests) {
        super(searchRequests);
    }

    @Override
    protected Specification<SplitBillGroup> createSpecification(SearchRequest searchRequest) {
        return new SplitBillGroupSpecification(searchRequest);
    }
}
