package com.expenses.jonsnow.mapper;

import com.expenses.jonsnow.dto.SplitBillDTO;
import com.expenses.jonsnow.dto.SplitBillShareDTO;
import com.expenses.jonsnow.dto.request.SplitBillRequest;
import com.expenses.jonsnow.dto.request.SplitBillShareRequest;
import com.expenses.jonsnow.model.SplitBill;
import com.expenses.jonsnow.model.SplitBillShare;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SplitBillMapper extends BaseMapper<SplitBill, SplitBillDTO, SplitBillRequest> {

    @Override
    @Mapping(
            target = "splitBillShareList" ,
            source = "splitBillShareList" ,
            qualifiedByName = "splitBillShareListToDTO"
    )
    @Mapping( target = "splitBillGroup.memberList",ignore = true)
    SplitBillDTO mapEntityToDTO(SplitBill splitBill);

    @Named("splitBillShareListToDTO")
    List<SplitBillShareDTO> mapSplitBillShareListToDTOList(List<SplitBillShare> splitBillShares);

    @Mapping(target = "bill",ignore = true)
    SplitBillShareDTO mapSplitBillShareToDTO(SplitBillShare splitBillShare);

    @Override
    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "splitBillShareList", ignore = true)
    void mapRequestToEntity(SplitBillRequest splitBillRequest,@MappingTarget SplitBill splitBill);
}
