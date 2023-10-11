package com.expenses.jonsnow.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;


    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    private List<Transaction> transactions;

//    @OneToMany(
//            mappedBy = "user",
//            fetch = FetchType.LAZY
//    )
//    private List<Category> categories;

    @OneToMany(
            mappedBy = "paidBy",
            fetch = FetchType.LAZY
    )
    private List<SplitBill> splitBills;

    @OneToMany(
            mappedBy = "user",
            targetEntity = Budget.class,
            fetch = FetchType.LAZY
    )
    private List<Budget> budgets;


    @OneToMany(
            mappedBy = "user",
            targetEntity = SplitBillShare.class,
            fetch = FetchType.LAZY
    )
    private List<SplitBillShare> splitBillShares;

    @OneToMany(
            mappedBy = "user",
            targetEntity = SplitBillGroup.class,
            fetch = FetchType.LAZY
    )
    private List<SplitBillGroup> splitBillGroupsCreatedList;

    @OneToMany(
            mappedBy = "paidBy",
            targetEntity = SplitBill.class,
            fetch = FetchType.LAZY
    )
    private List<SplitBill> billsPaidList;

    @OneToMany(
            mappedBy = "member",
            fetch = FetchType.LAZY
    )
    private List<SplitBillGroupMember> groupsList;

//    @OneToOne(fetch = FetchType.LAZY)
//    private UserPreferences userPreferences;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    private List<Transactee> transactees;
}
