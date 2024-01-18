package com.expenses.jonsnow.specification;

import com.expenses.jonsnow.model.SplitBillGroup;
import jakarta.persistence.criteria.*;

public class SplitBillGroupSpecification extends BaseSpecification<SplitBillGroup> {
    public SplitBillGroupSpecification(SearchRequest searchRequest) {
        super(searchRequest);
    }

    @Override
    public Predicate toPredicate(Root<SplitBillGroup> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return switch (searchRequest.getKey()){
            default -> super.toPredicate(root, query, cb);
        };
    }

    @Override
    protected Path<String> getPath(SearchRequest searchRequest, Root<SplitBillGroup> root) {
        return switch (searchRequest.getKey()){
            default -> super.getPath(searchRequest, root);
        };
    }

    @Override
    protected Object stringToEnum(String value) {
        return null;
    }
}
