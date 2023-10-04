package com.expenses.jonsnow.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "transactees")
public class Transactee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(
            mappedBy = "transactee",
            fetch = FetchType.LAZY
    )
    private List<Transaction> transactions;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
