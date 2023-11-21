package com.expenses.jonsnow.mapper;

import com.expenses.jonsnow.dto.TransactionDTO;
import com.expenses.jonsnow.dto.request.TransactionRequest;
import com.expenses.jonsnow.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    List<TransactionDTO> map(List<Transaction> transactions);

    @Mapping(target = "type",source = "type.type")
    @Mapping(target = "paymentMode", source = "paymentMode.mode")
    TransactionDTO map(Transaction transaction);

    @Mapping(target = "transactee.id", source = "transacteeId")
    Transaction map(TransactionRequest request);

}
