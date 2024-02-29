package com.expenses.jonsnow.specification;

import com.expenses.jonsnow.model.SplitBillGroup;
import com.expenses.jonsnow.model.SplitBillShare;
import jakarta.persistence.criteria.*;

import java.util.Objects;

public class SplitBillShareSpecification extends BaseSpecification<SplitBillShare> {

    public SplitBillShareSpecification(SearchRequest searchRequest) {
        super(searchRequest);
    }

    @Override
    public Predicate toPredicate(Root<SplitBillShare> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return switch (searchRequest.getKey()){
            case "billName" -> cb.like(getPath(searchRequest,root),'%' + searchRequest.getValue()+'%');
            case "group" -> {
                if(Objects.equals(String.valueOf(0),searchRequest.getValue())){
                    Subquery<Long> subquery = query.subquery(Long.class);
                    Root<SplitBillShare> subRoot = subquery.from(SplitBillShare.class);
                    subquery.select(cb.max(subRoot.get("bill").get("splitBillGroup").get("id")));

                    yield cb.equal(root.join("bill").join("splitBillGroup").get("id"), subquery);
                } else {
                    yield cb.equal(getPath(searchRequest,root),searchRequest.getValue());
                }
            }
            case "user" -> cb.equal(getPath(searchRequest,root),searchRequest.getValue());
            default -> super.toPredicate(root, query, cb);
        };
    }

    @Override
    protected Path<String> getPath(SearchRequest searchRequest, Root<SplitBillShare> root) {
        return switch (searchRequest.getKey()){
            case "billName" -> root.join("bill").get("bill");
            case "group" -> root.join("bill").join("splitBillGroup").get("id");
            case "user" -> root.join("user").get("id");
            default -> super.getPath(searchRequest, root);
        };
    }

    @Override
    protected Object stringToEnum(String value) {
        return null;
    }

}
