package com.expenses.jonsnow.model;

import jakarta.persistence.*;

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
}
