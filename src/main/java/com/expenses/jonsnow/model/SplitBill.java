package com.expenses.jonsnow.model;

import com.expenses.jonsnow.model.enums.SplitAlgo;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "split_bills")
@Data
public class SplitBill extends AuditCreatedBy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bill")
    private String bill;

    @Column(name = "amount")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "split_algo")
    private SplitAlgo splitAlgo;

    @Column(name = "date")
    private Instant date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "split_bill_group_id",
            nullable = false
    )
    private SplitBillGroup splitBillGroup;

    @ManyToOne
    @JoinColumn(
            name = "paid_by",
            nullable = false
    )
    private User paidBy;

    @OneToMany(
            mappedBy = "splitBill",
            fetch = FetchType.LAZY
    )
    private List<Transaction> transactions;

    @OneToMany(
            mappedBy = "bill",
            fetch = FetchType.LAZY
    )
    private List<SplitBillShare> splitBillShareList;

}