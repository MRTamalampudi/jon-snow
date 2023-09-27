package com.expenses.jonsnow.model;

import jakarta.persistence.*;

@Table(name = "categories")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
