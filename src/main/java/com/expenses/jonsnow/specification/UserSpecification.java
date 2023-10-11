package com.expenses.jonsnow.specification;

import com.expenses.jonsnow.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class UserSpecification extends BaseSpecification<User>{

    public UserSpecification(SearchRequest searchRequest){
        super(searchRequest);
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = super.toPredicate(root, query, cb);
        return predicate;
    }

    @Override
    protected Object stringToEnum(String value) {
        return null;
    }
}
