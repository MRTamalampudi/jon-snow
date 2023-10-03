package com.expenses.jonsnow.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "circle_members")
public class Circle {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "member_id",
            nullable = false
    )
    private User member;

}
