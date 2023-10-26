package com.expenses.jonsnow.service;

import com.expenses.jonsnow.specification.BaseSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CRUDService<Entity,DTO> {
    Page<DTO> index(
            Specification<Entity> specification,
            Pageable pageable
    );

    Entity findById(Long entityId);

    void deleteById(Long enityId);

    Entity create(Entity entity);

    List<Entity> findAll(Specification<Entity> specification);
}
