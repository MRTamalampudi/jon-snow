package com.expenses.jonsnow.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionSummaryDTO {
    private BigDecimal lent;
    private BigDecimal owe;
    private BigDecimal cashIn;
    private BigDecimal cashOut;
}
