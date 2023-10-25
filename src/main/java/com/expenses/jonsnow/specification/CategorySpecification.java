package com.expenses.jonsnow.specification;

import com.expenses.jonsnow.model.Category;

public class CategorySpecification extends BaseSpecification<Category> {
    public CategorySpecification(SearchRequest searchRequest) {
        super(searchRequest);
    }

    @Override
    protected Object stringToEnum(String value) {
        return null;
    }
}
