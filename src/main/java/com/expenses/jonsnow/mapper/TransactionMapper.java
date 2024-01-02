package com.expenses.jonsnow.mapper;

import com.expenses.jonsnow.dto.TransactionDTO;
import com.expenses.jonsnow.dto.request.TransactionRequest;
import com.expenses.jonsnow.model.Transaction;
import com.expenses.jonsnow.model.enums.PaymentMode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends BaseMapper<Transaction, TransactionDTO, TransactionRequest> {

    @Mapping(target = "type",source = "type.type")
    @Mapping(target = "paymentMode", source = "paymentMode.mode")
    TransactionDTO mapEntityToDTO(Transaction transaction);


    @Mapping(target = "paymentMode", expression = "java(PaymentMode.fromString(request.getPaymentMode()))")
    @Mapping(target = "type", expression = "java(TransactionType.fromString(request.getType()))" )
    Transaction map(TransactionRequest request);


}
