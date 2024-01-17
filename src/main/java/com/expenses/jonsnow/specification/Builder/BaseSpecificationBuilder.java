package com.expenses.jonsnow.specification.Builder;

import com.expenses.jonsnow.specification.Operator;
import com.expenses.jonsnow.specification.SearchRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public abstract class BaseSpecificationBuilder<T>{
    List<SearchRequest> searchRequests;

    public Specification<T> build(){
        if (searchRequests.size() == 0) {
            return null;
        }

        Specification<T> specification = createSpecification(searchRequests.get(0));
        for (int i = 1; i < searchRequests.size(); i++) {
            specification = Specification.where(specification).and(createSpecification(searchRequests.get(i)));
        }
        return specification;
    }

    protected abstract Specification<T> createSpecification(SearchRequest searchRequest);
}
