package com.expenses.jonsnow.mapper;

import com.expenses.jonsnow.dto.SplitBillDTO;
import com.expenses.jonsnow.dto.SplitBillShareDTO;
import com.expenses.jonsnow.dto.request.SplitBillRequest;
import com.expenses.jonsnow.model.SplitBill;
import com.expenses.jonsnow.model.SplitBillShare;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SplitBillMapper extends BaseMapper<SplitBill, SplitBillDTO, SplitBillRequest> {

    @Override
    @Mapping(
            target = "splitBillShareList" ,
            source = "splitBillShareList" ,
            qualifiedByName = "splitBillShareListToDTO"
    )
    SplitBillDTO mapEntityToDTO(SplitBill splitBill);

    @Named("splitBillShareListToDTO")
    List<SplitBillShareDTO> mapSplitBillShareListToDTOList(List<SplitBillShare> splitBillShares);

    @Mapping(target = "bill",ignore = true)
    SplitBillShareDTO mapSplitBillShareToDTO(SplitBillShare splitBillShare);
}
