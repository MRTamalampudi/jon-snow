package com.expenses.jonsnow.specification;

import com.expenses.jonsnow.model.Budget;

public class BudgetSpecification extends BaseSpecification<Budget>{
    public BudgetSpecification(SearchRequest searchRequest) {
        super(searchRequest);
    }

    @Override
    protected Object stringToEnum(String value) {
        return null;
    }
}
