package com.expenses.jonsnow.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.math.BigDecimal;

@Entity
@Table(name = "transaction_summary")
@Data
public class TransactionSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lent")
    private BigDecimal lent;

    @Column(name = "owe")
    private BigDecimal owe;

    @Column(name = "cash_in")
    private BigDecimal cashIn;

    @Column(name = "cash_out")
    private BigDecimal cashOut;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
