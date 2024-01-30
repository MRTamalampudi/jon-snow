package com.expenses.jonsnow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "group_members")
@AllArgsConstructor
@NoArgsConstructor
public class SplitBillGroupMember extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "member_id",
            nullable = false
    )
    private User member;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "group_id",
            nullable = false
    )
    @JsonBackReference
    private SplitBillGroup group;

    @Column(name = "owe_share")
    private BigDecimal oweShare = new BigDecimal(0);

    @Column(name = "lent_share")
    private BigDecimal lentShare = new BigDecimal(0);
}
