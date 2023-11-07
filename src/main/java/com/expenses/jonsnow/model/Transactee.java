package com.expenses.jonsnow.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "transactees")
@Data
public class Transactee extends AuditCreatedBy {

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

}
