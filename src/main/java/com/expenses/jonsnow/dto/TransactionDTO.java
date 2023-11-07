package com.expenses.jonsnow.dto;

import com.expenses.jonsnow.model.enums.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TransactionDTO {
    private Long id;
    private String note;
    private BigDecimal amount;
    private String type;
    private Instant date;
    private String paymentMode;
    private Long splitBillId;
    private Long budgetId;
    private Long transacteeId;
    private Long budgetItemId;
}
