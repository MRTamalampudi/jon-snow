package com.expenses.jonsnow.model;

import com.expenses.jonsnow.model.enums.PaymentMode;
import com.expenses.jonsnow.model.enums.SplitAlgo;
import com.expenses.jonsnow.model.enums.TransactionType;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "note")
    private String note;


    @Column(name = "amount")
    private Float amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType type;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;

    @Column(
            name = "date",
            columnDefinition = "TIMESTAMP"
    )
    private Instant date;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_mode")
    private PaymentMode paymentMode;

    @ManyToOne(targetEntity = SplitBill.class)
    @JoinColumn(name = "split_bill_id")
    private SplitBill splitBill;

//    @Column(name = "split_bill_id")
//    private Long splitBill_id;

    @ManyToOne(targetEntity = Budget.class)
    @JoinColumn(name = "budget_id")
    private Budget budget;

//    @Column(name = "budget_id")
//    private Long budgetId;

    @ManyToOne(targetEntity = Transactee.class)
    @JoinColumn(name = "transactee_id")
    private Transactee transactee;

//    @Column(name = "transactee_id")
//    private Long transacteeId;

    @OneToOne(
            targetEntity = BudgetItem.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "budget_item_id")
    private BudgetItem budgetItem;

//    @Column(name = "budget_item_id")
//    private Long budgetItemId;
}
