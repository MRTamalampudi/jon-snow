package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.UserDTO;
import com.expenses.jonsnow.mapper.UserMapper;
import com.expenses.jonsnow.model.User;
import com.expenses.jonsnow.repository.UserRepo;
import com.expenses.jonsnow.specification.Builder.UserSpecificationBuilder;
import com.expenses.jonsnow.specification.SearchRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService<User, UserDTO,UserDTO> {

    public UserService(UserRepo repo, UserMapper mapper) {
        super(repo, mapper);
    }

    public List<User> findAll(List<SearchRequest> requests) {
        Specification<User> specification = new UserSpecificationBuilder(requests).build();
        return super.findAll(specification);
    }
}
