package com.expenses.jonsnow.model;

import com.expenses.jonsnow.model.enums.SplitBillStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "split_bill_shares")
@Data
public class SplitBillShare extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "split_bill_id")
    private SplitBill bill;

    @Column(name = "amount")
    private Long amount = 0L;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private SplitBillStatus status;

    @ManyToOne(
            targetEntity = User.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "user_id",
            nullable = false,
            insertable = false,
            updatable = false
    )
    private User user;

    @Column(name = "user_id")
    private Long userId;
}
