package com.expenses.jonsnow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "group_members")
public class SplitBillGroupMember extends Audit {

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
    private BigDecimal oweShare = new BigDecimal(0);

    @Column(name = "lent_share")
    private BigDecimal lentShare = new BigDecimal(0);
}
