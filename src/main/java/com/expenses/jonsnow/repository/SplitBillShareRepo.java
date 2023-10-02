package com.expenses.jonsnow.repository;

import com.expenses.jonsnow.model.SplitBillShare;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface SplitBillShareRepo extends BaseRepo<SplitBillShare,Long> {
}
