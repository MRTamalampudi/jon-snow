package com.expenses.jonsnow.dto;

import lombok.Data;

@Data
public class TransactionSummaryDTO {
    private Long lent;
    private Long owe;
    private Long cashIn;
    private Long cashOut;
}
