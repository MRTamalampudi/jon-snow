package com.expenses.jonsnow.mapper;

import com.expenses.jonsnow.dto.UserDTO;
import com.expenses.jonsnow.dto.request.UserRequest;
import com.expenses.jonsnow.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDTO, UserRequest> {

}
