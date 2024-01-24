package com.expenses.jonsnow.dto;

import com.expenses.jonsnow.model.enums.SplitAlgo;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class SplitBillDTO {
    private Long id;
    private String bill;
    private BigDecimal amount;
    private SplitAlgo splitAlgo;
    private Instant date;
    private UserDTO paidBy;
    private UserDTO createdBy;
    private Instant createdAt;
    private Instant modifiedAt;
}
