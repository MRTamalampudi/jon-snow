package com.expenses.jonsnow.service;

import com.expenses.jonsnow.specification.BaseSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T> {
    Page<T> index(
            BaseSpecification<T> baseSpecification,
            Pageable pageable
    );
}
