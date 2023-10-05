package com.expenses.jonsnow.model;

import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

public abstract class BaseModel {
    @CreatedDate
    private Instant createdAt;

    private Instant modifiedAt;
}
