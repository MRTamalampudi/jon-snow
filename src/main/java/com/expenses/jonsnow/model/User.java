package com.expenses.jonsnow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(
            name = "email",
            nullable = false,
            unique = true
    )
    private String email;

    @Column(name = "password")
    private String password;


    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.EAGER
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Transaction> transactions;


    @OneToMany(
            mappedBy = "paidBy",
            fetch = FetchType.LAZY
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<SplitBill> splitBills;

    @OneToMany(
            mappedBy = "user",
            targetEntity = Budget.class,
            fetch = FetchType.LAZY
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Budget> budgets;


    @OneToMany(
            mappedBy = "user",
            targetEntity = SplitBillShare.class,
            fetch = FetchType.LAZY
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<SplitBillShare> splitBillShares;

    @OneToMany(
            mappedBy = "createdBy",
            targetEntity = SplitBillGroup.class,
            fetch = FetchType.LAZY
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<SplitBillGroup> splitBillGroupsCreatedList;

    @OneToMany(
            mappedBy = "paidBy",
            targetEntity = SplitBill.class,
            fetch = FetchType.LAZY
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<SplitBill> billsPaidList;

    @OneToMany(
            mappedBy = "member",
            fetch = FetchType.LAZY
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<SplitBillGroupMember> groupsList;


    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Transactee> transactees;
}
