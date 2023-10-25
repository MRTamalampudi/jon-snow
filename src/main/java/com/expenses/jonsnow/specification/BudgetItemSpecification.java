package com.expenses.jonsnow.specification;

import com.expenses.jonsnow.model.BudgetItem;

public class BudgetItemSpecification extends BaseSpecification<BudgetItem> {
    public BudgetItemSpecification(SearchRequest searchRequest) {
        super(searchRequest);
    }

    @Override
    protected Object stringToEnum(String value) {
        return null;
    }
}
