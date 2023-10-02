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
    @JoinColumn(name = "user_id")
    private User member;

//    @Column(name = "user_id")
//    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private SplitBillGroup group;

//    @Column(name = "group_id")
//    private Long groupId;

    @Column(name = "owe_share")
    private Long oweShare;

    @Column(name = "lent_share")
    private Long lentShare;
}
