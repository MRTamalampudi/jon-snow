package com.expenses.jonsnow.specification;

import com.expenses.jonsnow.model.Circle;

public class CircleSpecification extends BaseSpecification<Circle>{
    public CircleSpecification(SearchRequest searchRequest) {
        super(searchRequest);
    }

    @Override
    protected Object stringToEnum(String value) {
        return null;
    }
}
