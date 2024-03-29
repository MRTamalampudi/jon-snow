package com.expenses.jonsnow.repository;

import com.expenses.jonsnow.model.SplitBillGroupMember;
import com.expenses.jonsnow.model.SplitBillShare;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SplitBillGroupMemberRepo extends BaseRepo<SplitBillGroupMember,Long> {

}
