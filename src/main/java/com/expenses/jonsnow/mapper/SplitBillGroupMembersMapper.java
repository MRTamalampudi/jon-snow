package com.expenses.jonsnow.mapper;

import com.expenses.jonsnow.dto.SplitBillGroupMemberDTO;
import com.expenses.jonsnow.model.SplitBillGroupMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SplitBillGroupMembersMapper extends BaseMapper<SplitBillGroupMember, SplitBillGroupMemberDTO, SplitBillGroupMemberDTO> {

    @Mapping(target = "group.memberList",ignore = true)
    SplitBillGroupMemberDTO mapEntityToDTO(SplitBillGroupMember groupMember);

}
