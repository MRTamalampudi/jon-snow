package com.expenses.jonsnow.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TransactionRequest {
    private Long id;
    private String note;
    private BigDecimal amount;
    private String type;
    private Instant date;
    private String paymentMode;
    private Long transacteeId;
}
