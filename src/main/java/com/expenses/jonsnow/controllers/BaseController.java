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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.AbstractController;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@RequestBody Request request) throws
            InvocationTargetException,
            NoSuchMethodException,
            IllegalAccessException, NoSuchEntityException {
        service.update(request);
    }

    @DeleteMapping
    public void delete(List<SearchRequest> requests){
        List<Long> entityIds = requests.stream()
                .filter(searchRequest -> "id".equals(searchRequest.getKey()))
                .findFirst()
                .map(searchRequest -> searchRequest.getValues().stream()
                        .map(Long::parseLong)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
        service.deleteAllById(entityIds);
    }
}
