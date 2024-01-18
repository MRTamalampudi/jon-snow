package com.expenses.jonsnow.specification.Builder;

import com.expenses.jonsnow.model.SplitBillGroupMember;
import com.expenses.jonsnow.specification.SearchRequest;
import com.expenses.jonsnow.specification.SplitBillGroupMemberSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SplitBillGroupMemberSpecificationBuilder extends BaseSpecificationBuilder<SplitBillGroupMember> {
    public SplitBillGroupMemberSpecificationBuilder(List<SearchRequest> searchRequests) {
        super(searchRequests);
    }


    @Override
    protected Specification<SplitBillGroupMember> createSpecification(SearchRequest searchRequest) {
        return new SplitBillGroupMemberSpecification(searchRequest);
    }
}
