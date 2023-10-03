package com.expenses.jonsnow.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "budget")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(
            name = "date",
            columnDefinition = "TIMESTAMP"
    )
    private Instant date;

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
