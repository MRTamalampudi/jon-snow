package com.expenses.jonsnow.mapper;

import com.expenses.jonsnow.dto.TransactionDTO;
import com.expenses.jonsnow.dto.request.TransactionRequest;
import com.expenses.jonsnow.model.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    List<TransactionDTO> map(List<Transaction> transactions);
    TransactionDTO map(Transaction transaction);
    Transaction map(TransactionRequest request);
}
