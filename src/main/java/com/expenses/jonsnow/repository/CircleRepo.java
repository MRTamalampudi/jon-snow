package com.expenses.jonsnow.repository;

import com.expenses.jonsnow.model.Circle;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CircleRepo extends BaseRepo<Circle,Long> {
}
