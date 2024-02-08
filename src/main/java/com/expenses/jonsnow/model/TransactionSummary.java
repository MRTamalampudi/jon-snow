package com.expenses.jonsnow.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity
@Table(name = "transaction_summary")
@Data
public class TransactionSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lent")
    private Long lent;

    @Column(name = "owe")
    private Long owe;

    @Column(name = "cash_in")
    private Long cashIn;

    @Column(name = "cash_out")
    private Long cashOut;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
