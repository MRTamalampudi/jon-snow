package com.expenses.jonsnow.service;

import com.expenses.jonsnow.model.Category;
import com.expenses.jonsnow.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryService implements CRUDService<Category,Category> {

    private final CategoryRepo repo;
    @Override
    public Page<Category> index(Specification<Category> specification, Pageable pageable) {
        return repo.findAll(specification,pageable);
    }

    @Override
    public Optional<Category> findById(Long entityId) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long enityId) {

    }

    @Override
    public Category create(Category category) {
        return null;
    }

    @Override
    public List<Category> findAll(Specification<Category> specification) {
        return null;
    }
}
