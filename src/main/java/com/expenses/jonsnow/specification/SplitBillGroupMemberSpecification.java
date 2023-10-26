package com.expenses.jonsnow.specification;

import com.expenses.jonsnow.model.SplitBillGroupMember;

public class SplitBillGroupMemberSpecification extends BaseSpecification<SplitBillGroupMember> {
    public SplitBillGroupMemberSpecification(SearchRequest searchRequest) {
        super(searchRequest);
    }

    @Override
    protected Object stringToEnum(String value) {
        return null;
    }
}
