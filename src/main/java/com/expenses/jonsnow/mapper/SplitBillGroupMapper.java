package com.expenses.jonsnow.mapper;

import com.expenses.jonsnow.dto.SplitBillGroupDTO;
import com.expenses.jonsnow.dto.SplitBillGroupMemberDTO;
import com.expenses.jonsnow.model.SplitBillGroup;
import com.expenses.jonsnow.model.SplitBillGroupMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SplitBillGroupMapper extends BaseMapper<SplitBillGroup, SplitBillGroupDTO, SplitBillGroupDTO>{

    @Override
    @Mapping(target = "memberList", source = "memberList",qualifiedByName = "mapMemberListToDTOList")
    SplitBillGroupDTO mapEntityToDTO(SplitBillGroup splitBillGroup);

    @Named("mapMemberListToDTOList")
    List<SplitBillGroupMemberDTO> mapMemberListToDTOList(List<SplitBillGroupMember> splitBillGroupMembers);
    @Mapping(target = "group",ignore = true)
    @Mapping(target = "member", source = "user")
    SplitBillGroupMemberDTO mapMembertoDTO(SplitBillGroupMember member);
}
