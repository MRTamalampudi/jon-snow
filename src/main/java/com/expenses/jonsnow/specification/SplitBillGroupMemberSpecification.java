package com.expenses.jonsnow.specification;

import com.expenses.jonsnow.model.SplitBillGroupMember;
import jakarta.persistence.criteria.*;

public class SplitBillGroupMemberSpecification extends BaseSpecification<SplitBillGroupMember> {
    public SplitBillGroupMemberSpecification(SearchRequest searchRequest) {
        super(searchRequest);
    }


    @Override
    public Predicate toPredicate(Root<SplitBillGroupMember> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return switch (searchRequest.getKey()){
            case "groupName" -> cb.like(getPath(searchRequest,root),'%' + searchRequest.getValue()+'%');
            case "group", "member" -> cb.equal(getPath(searchRequest,root),searchRequest.getValue());
            default -> super.toPredicate(root, query, cb);
        };
    }

    @Override
    protected Path<String> getPath(SearchRequest searchRequest, Root<SplitBillGroupMember> root) {
        return switch (searchRequest.getKey()){
            case "groupName" -> root.join("group").get("name");
            case "group" -> root.join("group").get("id");
            case "member" -> root.join("member").get("id");
            default -> super.getPath(searchRequest, root);
        };
    }

    @Override
    protected Object stringToEnum(String value) {
        return null;
    }
}
