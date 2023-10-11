package com.expenses.jonsnow.specification.Builder;

import com.expenses.jonsnow.model.User;
import com.expenses.jonsnow.specification.SearchRequest;
import com.expenses.jonsnow.specification.UserSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class UserSpecificationBuilder extends BaseSpecificationBuilder{

    public UserSpecificationBuilder(){
        super(new ArrayList<>());
    }
    @Override
    public Specification<User> build() {
        if (searchRequests.size() == 0) {
            return null;
        }

        Specification<User> specification = new UserSpecification(searchRequests.get(0));
        for(int i = 1 ; i < searchRequests.size() ; i++) {
            specification = Specification.where(specification).and(new UserSpecification(searchRequests.get(i)));
        }
        return specification;
    }
}
