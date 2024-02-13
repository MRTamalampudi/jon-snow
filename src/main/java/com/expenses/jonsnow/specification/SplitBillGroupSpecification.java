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
            case "recent" -> {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<SplitBillGroup> subRoot = subquery.from(SplitBillGroup.class);
                subquery.select(cb.max(subRoot.get("id")));

                yield cb.equal(root.get("id"), subquery);
            }
            default -> super.toPredicate(root, query, cb);
        };
    }

    @Override
    protected Path<String> getPath(SearchRequest searchRequest, Root<SplitBillGroup> root) {
        return switch (searchRequest.getKey()){
            case "recent" -> root.get("id");
            default -> super.getPath(searchRequest, root);
        };
    }

    @Override
    protected Object stringToEnum(String value) {
        return null;
    }
}
