package com.expenses.jonsnow.specification;

import com.expenses.jonsnow.model.SplitBill;

public class SplitBillSpecification extends BaseSpecification<SplitBill> {
    public SplitBillSpecification(SearchRequest searchRequest) {
        super(searchRequest);
    }

    @Override
    protected Object stringToEnum(String value) {
        return null;
    }
}
