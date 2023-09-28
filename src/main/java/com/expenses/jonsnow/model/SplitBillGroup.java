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

    private String name;

    private String avatar;

    @ManyToOne(
            targetEntity = User.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "created_by")
    private User user;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "date")
    private Instant date;

    @OneToMany(
            mappedBy = "group",
            fetch = FetchType.LAZY
    )
    private List<SplitBillGroupMember> memberList;


}
