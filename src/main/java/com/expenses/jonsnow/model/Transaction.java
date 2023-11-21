package com.expenses.jonsnow.model;

import com.expenses.jonsnow.model.enums.PaymentMode;
import com.expenses.jonsnow.model.enums.SplitAlgo;
import com.expenses.jonsnow.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transactions")
@Data
public class Transaction extends AuditCreatedBy{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "note")
    private String note;


    @Column(name = "amount")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType type;

    @Column(
            name = "date",
            columnDefinition = "TIMESTAMP",
            nullable = false
    )
    private Instant date;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_mode")
    private PaymentMode paymentMode;

    @ManyToOne(targetEntity = SplitBill.class)
    @JoinColumn(name = "split_bill_id")
    private SplitBill splitBill;

    @ManyToOne(targetEntity = Budget.class)
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @ManyToOne(targetEntity = Transactee.class)
    @JoinColumn(name = "transactee_id")
    private Transactee transactee;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(
            targetEntity = BudgetItem.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "budget_item_id")
    private BudgetItem budgetItem;
}
