package com.expenses.jonsnow.dto.request;

import com.expenses.jonsnow.model.Category;
import com.expenses.jonsnow.model.Transactee;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TransactionRequest {
    private Long id;
    private String note;
    private Long amount;
    private String type;
    private Instant date;
    private String paymentMode;
    private TransacteeRequest transactee;
    private Category category;
}
