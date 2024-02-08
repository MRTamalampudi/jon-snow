package com.expenses.jonsnow.mapper;

import com.expenses.jonsnow.dto.SplitBillDTO;
import com.expenses.jonsnow.dto.request.SplitBillRequest;
import com.expenses.jonsnow.model.SplitBill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SplitBillMapper extends BaseMapper<SplitBill, SplitBillDTO, SplitBillRequest> {
}
