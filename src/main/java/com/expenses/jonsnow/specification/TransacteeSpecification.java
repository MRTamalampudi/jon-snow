package com.expenses.jonsnow.specification;

import com.expenses.jonsnow.model.Transactee;

public class TransacteeSpecification extends BaseSpecification<Transactee> {
    public TransacteeSpecification(SearchRequest searchRequest) {
        super(searchRequest);
    }

    @Override
    protected Object stringToEnum(String value) {
        return null;
    }
}
