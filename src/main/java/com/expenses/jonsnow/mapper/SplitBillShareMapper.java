package com.expenses.jonsnow.mapper;

import com.expenses.jonsnow.dto.SplitBillShareDTO;
import com.expenses.jonsnow.dto.request.SplitBillShareRequest;
import com.expenses.jonsnow.model.SplitBillShare;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SplitBillShareMapper extends BaseMapper<SplitBillShare, SplitBillShareDTO, SplitBillShareRequest> {

    @Override
    @Mapping(target = "bill.splitBillShareList",ignore = true)
    @Mapping(target = "bill.splitBillGroup",ignore = true)
    SplitBillShareDTO mapEntityToDTO(SplitBillShare splitBillShare);

    List<SplitBillShare> mapRequestListToEntityList(List<SplitBillShareRequest> splitBillShareRequests);

    @Mapping(target = "id",ignore = true)
    void mapRequestToEntity(SplitBillShareRequest request, @MappingTarget SplitBillShare share);

}
