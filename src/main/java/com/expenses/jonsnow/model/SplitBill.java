package com.expenses.jonsnow.model;

import com.expenses.jonsnow.model.enums.SplitAlgo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
    private Long amount;

    @Column(name = "date")
    private Instant date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "split_bill_group_id",
            nullable = false,
            insertable = false,
            updatable = false
    )
    @ToString.Exclude
    private SplitBillGroup splitBillGroup;

    @Column(name = "split_bill_group_id")
    private Long splitBillGroupId;

    @ManyToOne
    @JoinColumn(
            name = "paid_by",
            nullable = false,
            insertable = false,
            updatable = false
    )
    private User paidBy;

    @Column(name = "paid_by")
    private Long paidUserId;

    @OneToMany(
            mappedBy = "bill",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<SplitBillShare> splitBillShareList;

    @PrePersist
    public void prePersist() {
        splitBillShareList.forEach(splitBillShare -> splitBillShare.setBill(this));
    }
}