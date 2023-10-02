package com.expenses.jonsnow.repository;

import com.expenses.jonsnow.model.SplitBill;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface SplitBillRepo extends BaseRepo<SplitBill,Long> {
}
