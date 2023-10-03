package com.expenses.jonsnow.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@Table(name = "split_bill_groups")
public class SplitBillGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(name = "avatar")
    private String avatar;

    @ManyToOne(
            targetEntity = User.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "created_by")
    private User user;

    @Column(name = "date")
    private Instant date;

    @OneToMany(
            mappedBy = "group",
            fetch = FetchType.LAZY
    )
    private List<SplitBillGroupMember> memberList;
}
