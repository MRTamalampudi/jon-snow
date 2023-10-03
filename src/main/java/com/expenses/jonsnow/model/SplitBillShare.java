package com.expenses.jonsnow.model;

import com.expenses.jonsnow.model.enums.SplitBillStatus;
import com.expenses.jonsnow.model.enums.TransactionType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "split_bill_shares")
public class SplitBillShare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "split_bill_id")
    private SplitBill bill;

    @Column(
            name = "transaction_type",
            nullable = false
    )
    private TransactionType type; //Allow only OWE and LENT;

    @Column(name = "amount")
    private BigDecimal amount = BigDecimal.ZERO;

    @Column(name = "status")
    private SplitBillStatus status;

    @ManyToOne(
            targetEntity = User.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;
}
