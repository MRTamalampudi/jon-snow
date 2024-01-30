package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.exceptions.NoSuchEntityException;
import com.expenses.jonsnow.mapper.BaseMapper;
import com.expenses.jonsnow.service.BaseService;
import com.expenses.jonsnow.specification.BaseSpecification;
import com.expenses.jonsnow.specification.Builder.BaseSpecificationBuilder;
import com.expenses.jonsnow.specification.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.AbstractController;

import java.util.List;

public abstract class BaseController<Entity,DTO,Request> {

    private final BaseService<Entity,DTO,Request> service;
    private final BaseMapper<Entity,DTO,Request> mapper;

    private final BaseSpecificationBuilder<Entity> specificationBuilder;

    public BaseController(
            BaseService<Entity, DTO, Request> service,
            BaseMapper<Entity, DTO, Request> mapper,
            BaseSpecificationBuilder<Entity> specificationBuilder
    ) {
        this.service = service;
        this.mapper = mapper;
        this.specificationBuilder = specificationBuilder;
    }

    @GetMapping
    public Page<DTO> index(
            List<SearchRequest> requests,
            Pageable pageable
    ) {
        this.specificationBuilder.setSearchRequests(requests);
        Specification<Entity> specification = this.specificationBuilder.build();
        return this.service.index(specification,pageable);
    }

    @GetMapping("/{entityId}")
    public DTO get(@PathVariable("entityId") Long entityId) throws NoSuchEntityException {
        return mapper.mapEntityToDTO(service.findById(entityId));
    }

    @PostMapping
    public DTO create(@RequestBody Request request) throws NoSuchEntityException {
        return mapper.mapEntityToDTO(
                service.create(
                        mapper.mapRequestToEntity(request)
                )
        );
    }
}
