package com.expenses.jonsnow.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User extends Audit implements UserDetails {

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

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String userName;


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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
