package com.expenses.jonsnow.mapper;

import com.expenses.jonsnow.dto.SplitBillShareDTO;
import com.expenses.jonsnow.model.SplitBillShare;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SplitBillShareMapper extends BaseMapper<SplitBillShare, SplitBillShareDTO,SplitBillShareDTO> {

    @Override
    @Mapping(target = "bill.splitBillShareList",ignore = true)
    SplitBillShareDTO mapEntityToDTO(SplitBillShare splitBillShare);
}
