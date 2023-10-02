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
    @JoinColumn(name = "user_id")
    private User user;

//    @Column(name = "user_id")
//    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private User member;

//    @Column(name = "member_id")
//    private Long memberId;
}
