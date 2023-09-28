package com.expenses.jonsnow.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;
    @Column(name = "dark_mode")
    private Boolean darkMode;

    private String currency;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    private List<Transaction> transactions;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    private List<Category> categories;

    @OneToMany(
            mappedBy = "user",
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
            targetEntity = BillShare.class,
            fetch = FetchType.LAZY
    )
    private List<BillShare> billShares;

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


}
