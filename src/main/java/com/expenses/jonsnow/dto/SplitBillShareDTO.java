package com.expenses.jonsnow.dto;

import com.expenses.jonsnow.model.SplitBill;
import com.expenses.jonsnow.model.User;
import com.expenses.jonsnow.model.enums.SplitBillStatus;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SplitBillShareDTO {
    private Long id;
    private SplitBillDTO bill;
    private Long amount;
    private SplitBillStatus status;
    private UserDTO user;
}
