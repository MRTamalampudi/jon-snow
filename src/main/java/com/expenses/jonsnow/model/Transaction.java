package com.expenses.jonsnow.model;

import com.expenses.jonsnow.model.enums.TransactionType;
import jakarta.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "note")
    private String note;


    private Float amount;

    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;


}
