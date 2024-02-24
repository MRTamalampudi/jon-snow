package com.expenses.jonsnow.dto;

import com.expenses.jonsnow.model.SplitBillShare;
import com.expenses.jonsnow.model.enums.SplitAlgo;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class SplitBillDTO {
    private Long id;
    private String bill;
    private Long amount;
    private SplitBillGroupDTO splitBillGroup;
    private SplitAlgo splitAlgo;
    private Instant date;
    private UserDTO paidBy;
    private UserDTO createdBy;
    private Instant createdAt;
    private Instant modifiedAt;
    private List<SplitBillShareDTO> splitBillShareList;
}
