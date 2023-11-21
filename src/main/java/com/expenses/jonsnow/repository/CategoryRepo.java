package com.expenses.jonsnow.repository;

import com.expenses.jonsnow.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends BaseRepo<Category,Long> {
}
