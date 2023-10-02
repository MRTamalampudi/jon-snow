package com.expenses.jonsnow.repository;

import com.expenses.jonsnow.model.SplitBillGroup;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface SplitBillGroupRepo extends BaseRepo<SplitBillGroup,Long> {
}
