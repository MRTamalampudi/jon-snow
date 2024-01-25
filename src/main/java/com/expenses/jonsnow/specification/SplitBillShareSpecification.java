package com.expenses.jonsnow.specification;

import com.expenses.jonsnow.model.SplitBillShare;
import jakarta.persistence.criteria.*;

public class SplitBillShareSpecification extends BaseSpecification<SplitBillShare> {
    public SplitBillShareSpecification(SearchRequest searchRequest) {
        super(searchRequest);
    }

    @Override
    public Predicate toPredicate(Root<SplitBillShare> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return switch (searchRequest.getKey()){
            case "groupId", "user" -> cb.equal(getPath(searchRequest,root),searchRequest.getValue());
            default -> super.toPredicate(root, query, cb);
        };
    }

    @Override
    protected Path<String> getPath(SearchRequest searchRequest, Root<SplitBillShare> root) {
        return switch (searchRequest.getKey()){
            case "groupId" -> root.join("bill").join("splitBillGroup").get("id");
            case "user" -> root.join("user").get("id");
            default -> super.getPath(searchRequest, root);
        };
    }

    @Override
    protected Object stringToEnum(String value) {
        return null;
    }

}
