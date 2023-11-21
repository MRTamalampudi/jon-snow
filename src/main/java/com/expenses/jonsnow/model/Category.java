package com.expenses.jonsnow.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "categories")
@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
