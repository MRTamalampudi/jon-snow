package com.expenses.jonsnow.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "group_members")
public class SplitBillGroupMember {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "member_id",
            nullable = false
    )
    private User member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "group_id",
            nullable = false
    )
    private SplitBillGroup group;

    @Column(name = "owe_share")
    private Long oweShare;

    @Column(name = "lent_share")
    private Long lentShare;
}
