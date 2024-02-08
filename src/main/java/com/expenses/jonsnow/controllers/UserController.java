package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.config.URLConstants;
import com.expenses.jonsnow.dto.UserDTO;
import com.expenses.jonsnow.mapper.UserMapper;
import com.expenses.jonsnow.service.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = URLConstants.USER)
public class UserController {

    private final UserMapper mapper;

    public UserController(UserMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping
    public UserDTO get(){
        return mapper.mapEntityToDTO(UserContext.getUser());
    }
}


