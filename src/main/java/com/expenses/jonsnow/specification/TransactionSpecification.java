package com.expenses.jonsnow.specification;

import com.expenses.jonsnow.model.Transaction;
import com.expenses.jonsnow.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class TransactionSpecification extends BaseSpecification<Transaction> {
    public TransactionSpecification(SearchRequest searchRequest) {
        super(searchRequest);
    }
    @Override
    protected Object stringToEnum(String value) {
        return null;
    }
}
