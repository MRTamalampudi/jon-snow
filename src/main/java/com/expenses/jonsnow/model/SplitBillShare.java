package com.expenses.jonsnow.model;

import com.expenses.jonsnow.model.enums.SplitBillStatus;
import com.expenses.jonsnow.model.enums.TransactionType;
import jakarta.persistence.*;

@Entity
@Table(name = "split_bill_members")
public class SplitBillShare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "split_bill_id")
    private SplitBill bill;

//    @Column(name = "split_bill_id")
//    private Long splitBillId;

    @Column(name = "transaction_type")
    private TransactionType type; //Allow only OWE and LENT;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "status")
    private SplitBillStatus status;

    @ManyToOne(
            targetEntity = User.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private User user;

//    @Column(name = "user_id")
//    private Long userId;
}
