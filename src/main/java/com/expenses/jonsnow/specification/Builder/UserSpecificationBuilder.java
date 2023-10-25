package com.expenses.jonsnow.specification.Builder;

import com.expenses.jonsnow.model.User;
import com.expenses.jonsnow.specification.SearchRequest;
import com.expenses.jonsnow.specification.UserSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class UserSpecificationBuilder extends BaseSpecificationBuilder<User>{

    public UserSpecificationBuilder(){
        super(new ArrayList<>());
    }

    @Override
    public Specification<User> createSpecification(SearchRequest searchRequest) {
        return new UserSpecification(searchRequest);
    }
}
