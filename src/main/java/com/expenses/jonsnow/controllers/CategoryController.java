package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.config.URLConstants;
import com.expenses.jonsnow.model.Category;
import com.expenses.jonsnow.service.CategoryService;
import com.expenses.jonsnow.specification.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = URLConstants.CATEGORY)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController implements CRUDController<Category,Category,Category>{

    private final CategoryService service;
    @Override
    public Page<Category> index(List<SearchRequest> requests, Pageable pageable) {
        return service.index(null,pageable);
    }

    @Override
    public Category get(Long entityId) {
        return null;
    }

    @Override
    public Category create(Category category) {
        return null;
    }

    @Override
    public void delete(Long entityId) {

    }
}
