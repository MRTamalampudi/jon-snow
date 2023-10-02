package com.expenses.jonsnow.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "budget")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;  

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "budget"
    )
    private List<BudgetItem> budgetItems;

}
