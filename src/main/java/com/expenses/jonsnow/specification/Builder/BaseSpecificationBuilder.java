package com.expenses.jonsnow.specification.Builder;

import com.expenses.jonsnow.specification.Operator;
import com.expenses.jonsnow.specification.SearchRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public abstract class BaseSpecificationBuilder{
    List<SearchRequest> searchRequests;

    public void addSearchRequests(String key,String operatorString,String value){
        Operator operator= Operator.getOperator(operatorString);
        List<String> values = Operator.IN.equals(operator) ?
                List.of(value.split("#")) :
                new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest(
                key,
                operator,
                values.size() > 0 ? null : value,
                values
        );
        this.searchRequests
                .add(searchRequest);
    }

    public abstract Specification<?> build();
}
