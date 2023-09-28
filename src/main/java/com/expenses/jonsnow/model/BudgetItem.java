package com.expenses.jonsnow.model;

import jakarta.persistence.*;

@Entity
@Table(name = "budget_items")
public class BudgetItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item")
    private String item;

    @Column(name = "amount")
    private Float amount;

    @ManyToOne(
            targetEntity = Budget.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @Column(name = "budget_id")
    private Long budget_id;

    @OneToOne(
            targetEntity = Transaction.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @Column(name = "transaction_id")
    private Long transactionId;
}
