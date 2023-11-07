package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.specification.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface CRUDController<Entity,DTO,Request> {
    @GetMapping
    Page<DTO> index(
            List<SearchRequest> requests,
            Pageable pageable
    );

    @GetMapping("/{id}")
    DTO get(@PathVariable("id") Long entityId);

    @PostMapping
    DTO create(@RequestBody Request request);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long entityId);


}
