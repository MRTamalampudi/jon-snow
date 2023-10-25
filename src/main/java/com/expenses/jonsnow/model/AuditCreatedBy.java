package com.expenses.jonsnow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditCreatedBy extends Audit {
    @CreatedBy
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User createdBy;
}
