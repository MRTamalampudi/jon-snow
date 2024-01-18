package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.SplitBillGroupMemberDTO;
import com.expenses.jonsnow.mapper.BaseMapper;
import com.expenses.jonsnow.mapper.SplitBillGroupMembersMapper;
import com.expenses.jonsnow.model.SplitBillGroupMember;
import com.expenses.jonsnow.repository.BaseRepo;
import com.expenses.jonsnow.repository.SplitBillGroupMemberRepo;
import org.springframework.stereotype.Service;

@Service
public class SplitBillGroupMemberService extends BaseService<SplitBillGroupMember, SplitBillGroupMemberDTO,SplitBillGroupMemberDTO> {

    private final SplitBillGroupMemberRepo repo;
    private final SplitBillGroupMembersMapper mapper;

    public SplitBillGroupMemberService(SplitBillGroupMemberRepo repo, SplitBillGroupMembersMapper mapper) {
        super(repo, mapper);
        this.repo = repo;
        this.mapper = mapper;
    }
}
