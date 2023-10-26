package com.expenses.jonsnow.specification;

import com.expenses.jonsnow.model.SplitBillGroup;

public class SplitBillGroupSpecification extends BaseSpecification<SplitBillGroup> {
    public SplitBillGroupSpecification(SearchRequest searchRequest) {
        super(searchRequest);
    }

    @Override
    protected Object stringToEnum(String value) {
        return null;
    }
}
