package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.specification.Builder.BaseSpecificationBuilder;
import com.expenses.jonsnow.specification.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface BaseController<DTO,Request> {
    @GetMapping
    List<DTO> index(List<SearchRequest> requests, Pageable pageable);

    @GetMapping("/{id}")
    DTO get(@PathVariable("id") Long entityId);

    @PostMapping
    Request create(@RequestBody Request request);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long entityId);


}
