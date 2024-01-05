package com.expenses.jonsnow.mapper;

import com.expenses.jonsnow.dto.TransactionSummaryDTO;
import com.expenses.jonsnow.model.TransactionSummary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionSummaryMapper extends BaseMapper<TransactionSummary, TransactionSummaryDTO,TransactionSummaryDTO> {
}
