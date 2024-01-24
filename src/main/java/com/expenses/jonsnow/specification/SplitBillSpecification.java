package com.expenses.jonsnow.specification;

import com.expenses.jonsnow.model.SplitBill;
import jakarta.persistence.criteria.*;

public class SplitBillSpecification extends BaseSpecification<SplitBill> {
    public SplitBillSpecification(SearchRequest searchRequest) {
        super(searchRequest);
    }

    @Override
    public Predicate toPredicate(Root<SplitBill> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return switch (searchRequest.getKey()){
            case "groupId" -> cb.equal(getPath(searchRequest,root),searchRequest.getValue());
            default -> super.toPredicate(root, query, cb);
        };
    }

    @Override
    protected Path<String> getPath(SearchRequest searchRequest, Root<SplitBill> root) {
        return switch (searchRequest.getKey()){
            case "groupId" -> root.join("splitBillGroup").get("id");
            default -> super.getPath(searchRequest, root);
        };
    }

    @Override
    protected Object stringToEnum(String value) {
        return null;
    }
}
