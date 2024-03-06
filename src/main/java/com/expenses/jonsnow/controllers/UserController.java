package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.config.URLConstants;
import com.expenses.jonsnow.dto.UserDTO;
import com.expenses.jonsnow.dto.request.UserRequest;
import com.expenses.jonsnow.mapper.UserMapper;
import com.expenses.jonsnow.model.User;
import com.expenses.jonsnow.repository.UserRepo;
import com.expenses.jonsnow.service.UserContext;
import com.expenses.jonsnow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = URLConstants.USER)
public class UserController {

    private final UserMapper mapper;
    private final UserService service;

    public UserController(UserMapper mapper, UserService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping
    public UserDTO get(){
        return mapper.mapEntityToDTO(UserContext.getUser());
    }

    @PostMapping("/signup")
    public void create(@RequestBody UserRequest userRequest){
        User user = mapper.mapRequestToEntity(userRequest);
        service.create(user);
    }
}


