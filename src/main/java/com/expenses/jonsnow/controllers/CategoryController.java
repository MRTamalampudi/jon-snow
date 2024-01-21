package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.config.URLConstants;
import com.expenses.jonsnow.dto.CategoryDTO;
import com.expenses.jonsnow.mapper.BaseMapper;
import com.expenses.jonsnow.mapper.CategoryMapper;
import com.expenses.jonsnow.model.Category;
import com.expenses.jonsnow.service.BaseService;
import com.expenses.jonsnow.service.CategoryService;
import com.expenses.jonsnow.specification.Builder.CategorySpecificationBuilder;
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
public class CategoryController extends BaseController<Category, CategoryDTO,CategoryDTO>{

    private final CategoryService service;
    private static final CategorySpecificationBuilder builder = new CategorySpecificationBuilder(null);

    public CategoryController(CategoryService service, CategoryMapper mapper) {
        super(service, mapper,builder);
        this.service = service;
    }
}
