package com.expenses.jonsnow.mapper;

import com.expenses.jonsnow.dto.TransacteeDTO;
import com.expenses.jonsnow.dto.request.TransacteeRequest;
import com.expenses.jonsnow.model.Transactee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransacteeMapper extends BaseMapper<Transactee,TransacteeDTO,TransacteeRequest> {

}
