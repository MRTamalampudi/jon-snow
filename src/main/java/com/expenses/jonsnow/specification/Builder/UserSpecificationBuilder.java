package com.expenses.jonsnow.specification.Builder;

import com.expenses.jonsnow.model.User;
import com.expenses.jonsnow.specification.SearchRequest;
import com.expenses.jonsnow.specification.UserSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecificationBuilder extends BaseSpecificationBuilder<User>{

    public UserSpecificationBuilder(List<SearchRequest> requests){
        super(requests);
    }

    @Override
    public Specification<User> createSpecification(SearchRequest searchRequest) {
        return new UserSpecification(searchRequest);
    }
}
