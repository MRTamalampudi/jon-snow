package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.UserDTO;
import com.expenses.jonsnow.dto.request.UserRequest;
import com.expenses.jonsnow.mapper.UserMapper;
import com.expenses.jonsnow.model.TransactionSummary;
import com.expenses.jonsnow.model.User;
import com.expenses.jonsnow.repository.UserRepo;
import com.expenses.jonsnow.specification.Builder.UserSpecificationBuilder;
import com.expenses.jonsnow.specification.SearchRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService extends BaseService<User, UserDTO, UserRequest> {

    private TransactionSummaryService summaryService;

    public UserService(UserRepo repo, UserMapper mapper,TransactionSummaryService summaryService) {
        super(repo, mapper);
        this.summaryService = summaryService;
    }

    public List<User> findAll(List<SearchRequest> requests) {
        Specification<User> specification = new UserSpecificationBuilder(requests).build();
        return super.findAll(specification);
    }

    @Override
    public User create(User user) {
        User user1 = super.create(user);
        TransactionSummary summary = new TransactionSummary();
        summary.setUser(user1);
        summaryService.create(summary);
        return user1;
    }
}
