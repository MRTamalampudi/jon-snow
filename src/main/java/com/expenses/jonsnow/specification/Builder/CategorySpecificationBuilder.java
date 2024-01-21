package com.expenses.jonsnow.specification.Builder;

import com.expenses.jonsnow.model.Category;
import com.expenses.jonsnow.specification.CategorySpecification;
import com.expenses.jonsnow.specification.SearchRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class CategorySpecificationBuilder extends BaseSpecificationBuilder<Category> {

    public CategorySpecificationBuilder(List<SearchRequest> searchRequests) {
        super(searchRequests);
    }

    @Override
    protected Specification<Category> createSpecification(SearchRequest searchRequest) {
        return new CategorySpecification(searchRequest);
    }
}
