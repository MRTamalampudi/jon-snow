package com.expenses.jonsnow.specification.Builder;

import com.expenses.jonsnow.specification.Operator;
import com.expenses.jonsnow.specification.SearchRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSpecificationBuilder{
    List<SearchRequest> searchRequests;

    public void addSearchRequests(String key,String operatorString,String value){
        Operator operator= Operator.getOperator(operatorString);
        List<String> values = new ArrayList<>();
        if(Operator.IN.equals(operator)){
            values = List.of(value.split("#"));
        }
        SearchRequest searchRequest = new SearchRequest(
                key,
                operator,
                values.size() > 0 ? null : value,
                values
        );
        this.searchRequests.add(searchRequest);
    }

    public abstract Specification<?> build();
}
