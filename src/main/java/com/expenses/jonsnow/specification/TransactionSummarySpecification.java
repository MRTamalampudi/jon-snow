package com.expenses.jonsnow.specification;

import com.expenses.jonsnow.model.TransactionSummary;
import jakarta.persistence.criteria.*;

public class TransactionSummarySpecification extends BaseSpecification<TransactionSummary> {
    public TransactionSummarySpecification(SearchRequest searchRequest) {
        super(searchRequest);
    }

    @Override
    public Predicate toPredicate(Root<TransactionSummary> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return switch (searchRequest.getKey()){
            case "user" -> cb.equal(getPath(searchRequest,root),searchRequest.getValue());
            default -> super.toPredicate(root, query, cb);
        };
    }

    @Override
    protected Path<String> getPath(SearchRequest searchRequest, Root<TransactionSummary> root) {
        return switch (searchRequest.getKey()){
            case "user" -> root.join("user").get("id");
            default -> super.getPath(searchRequest, root);
        };
    }

    @Override
    protected Object stringToEnum(String value) {
        return null;
    }
}