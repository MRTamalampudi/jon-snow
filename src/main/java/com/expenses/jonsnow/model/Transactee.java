package com.expenses.jonsnow.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "transactee")
public class Transactee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(
            mappedBy = "transactee",
            fetch = FetchType.LAZY
    )
    private List<Transaction> transactions;
}
