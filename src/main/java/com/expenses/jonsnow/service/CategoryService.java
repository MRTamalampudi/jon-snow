package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.CategoryDTO;
import com.expenses.jonsnow.mapper.BaseMapper;
import com.expenses.jonsnow.mapper.CategoryMapper;
import com.expenses.jonsnow.model.Category;
import com.expenses.jonsnow.repository.BaseRepo;
import com.expenses.jonsnow.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService extends BaseService<Category, CategoryDTO,CategoryDTO> {

    private final CategoryRepo repo;
    private final CategoryMapper mapper;

    public CategoryService(CategoryRepo repo, CategoryMapper mapper) {
        super(repo, mapper);
        this.repo = repo;
        this.mapper = mapper;
    }
}
