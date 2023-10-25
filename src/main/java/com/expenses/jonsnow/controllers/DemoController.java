package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.model.User;
import com.expenses.jonsnow.model.UserPreferences;
import com.expenses.jonsnow.repository.UserPreferencesRepo;
import com.expenses.jonsnow.repository.UserRepo;
import com.expenses.jonsnow.specification.Builder.UserSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DemoController {

    private final UserRepo userRepo;
    private final UserPreferencesRepo userPreferencesRepo;

    @GetMapping
    public UserPreferences demo(){
        User user = new User();
        user.setFirstName("Mahendra Singh");
        user.setLastName("DHONI");
        user.setEmail("dhoni@gmail.com");
        user.setMobile("9373737377");

        user = userRepo.save(user);

        UserPreferences userPreferences= new UserPreferences();

        userPreferences.setUser(user);
        userPreferences.setCurrency("DOLLAR");
        userPreferences.setDarkMode(true);

        userPreferences = userPreferencesRepo.save(userPreferences);


        return userPreferences;
    }

    @GetMapping("/spec")
    public List<User> spec(UserSpecificationBuilder builder){
        Specification<User> userSpecification = builder.build();
        List<User> userList = userRepo.findAll(userSpecification);
        return userList;
    }
}
