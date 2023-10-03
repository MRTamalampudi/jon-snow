package com.expenses.jonsnow.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "budget_items")
@Data
public class BudgetItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "item",
            nullable = false
    )
    private String item;

    @Column(name = "amount")
    private Float amount;

    @ManyToOne(
            targetEntity = Budget.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "budget_id")
    private Budget budget;
}
