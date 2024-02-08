package com.expenses.jonsnow.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SplitBillGroupMemberDTO {
    private Long id;
    private Long oweShare;
    private Long lentShare;
    private SplitBillGroupDTO group;
    private UserDTO member;
}
