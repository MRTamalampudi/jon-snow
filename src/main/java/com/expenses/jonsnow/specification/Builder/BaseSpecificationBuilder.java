package com.expenses.jonsnow.specification.Builder;

import com.expenses.jonsnow.specification.Operator;
import com.expenses.jonsnow.specification.SearchRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.jpa.domain.Specification.where;

@AllArgsConstructor
public abstract class BaseSpecificationBuilder<T>{
    List<SearchRequest> searchRequests;

    public Specification<T> build(){
        if (searchRequests.size() == 0) {
            return null;
        }

        return searchRequests.stream()
                .map(this::createSpecification)
                .filter(Objects::nonNull)
                .reduce(where(null), Specification::and);
    }

    protected abstract Specification<T> createSpecification(SearchRequest searchRequest);
}
