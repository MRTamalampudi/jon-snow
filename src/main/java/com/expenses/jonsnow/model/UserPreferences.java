package com.expenses.jonsnow.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_preferences")
public class UserPreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dark_mode")
    private Boolean darkMode;

    @Column(name = "currency")
    private String currency;

    @OneToOne(
            fetch = FetchType.LAZY,
            mappedBy = "userPreferences"
    )
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;

    @Column(name = "user_id")
    private Long userId;
}
