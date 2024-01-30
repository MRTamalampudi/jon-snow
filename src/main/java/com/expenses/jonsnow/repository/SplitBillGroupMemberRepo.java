package com.expenses.jonsnow.repository;

import com.expenses.jonsnow.model.SplitBillGroupMember;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public interface SplitBillGroupMemberRepo extends BaseRepo<SplitBillGroupMember,Long> {
}
