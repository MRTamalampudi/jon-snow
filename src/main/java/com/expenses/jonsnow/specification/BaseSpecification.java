package com.expenses.jonsnow.specification;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public abstract class BaseSpecification<T> implements Specification<T> {

    protected SearchRequest searchRequest;

    public BaseSpecification(SearchRequest searchRequest){
        super();
        this.searchRequest = searchRequest;
    }
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return switch (searchRequest.getOperator()){
            case EQUALITY -> cb.equal(
                            getPath(searchRequest,root),
                            searchRequest.getValue());
            case LIKE -> cb.like(
                            getPath(searchRequest,root),
                            '%' + searchRequest.getValue()+'%');
            case GREATER_THAN -> cb.greaterThan(
                            getPath(searchRequest,root),
                            searchRequest.getValue());
            case LESS_THAN -> cb.lessThan(
                            getPath(searchRequest,root),
                            searchRequest.getValue());
            case GREATER_THAN_OR_EQUAL_TO -> cb.greaterThanOrEqualTo(
                            getPath(searchRequest,root),
                            searchRequest.getValue());
            case LESSER_THAN_OR_EQUAL_TO -> cb.lessThanOrEqualTo(
                            getPath(searchRequest,root),
                            searchRequest.getValue());
            case IN -> cb
                    .in(getPath(searchRequest,root))
                    .in(searchRequest.getValues());
        };
    }

    protected Path<String> getPath(SearchRequest searchRequest, Root<T> root){
        return root.get(searchRequest.getKey());
    }

    protected abstract Object stringToEnum(String value);
}
