package com.expenses.jonsnow.mapper;

import com.expenses.jonsnow.dto.SplitBillGroupDTO;
import com.expenses.jonsnow.model.SplitBillGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SplitBillGroupMapper extends BaseMapper<SplitBillGroup, SplitBillGroupDTO, SplitBillGroupDTO>{
}
