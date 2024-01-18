package com.expenses.jonsnow.dto;

import com.expenses.jonsnow.model.SplitBillGroupMember;
import lombok.Data;

import java.util.List;

@Data
public class SplitBillGroupDTO {
    private Long id;
    private String name;
    private List<SplitBillGroupMemberDTO> memberList;
}
