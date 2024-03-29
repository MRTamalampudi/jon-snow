package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.SplitBillGroupMemberDTO;
import com.expenses.jonsnow.mapper.BaseMapper;
import com.expenses.jonsnow.mapper.SplitBillGroupMembersMapper;
import com.expenses.jonsnow.model.SplitBillGroupMember;
import com.expenses.jonsnow.repository.BaseRepo;
import com.expenses.jonsnow.repository.SplitBillGroupMemberRepo;
import com.expenses.jonsnow.specification.Builder.SplitBillGroupMemberSpecificationBuilder;
import com.expenses.jonsnow.specification.Operator;
import com.expenses.jonsnow.specification.SearchRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SplitBillGroupMemberService extends BaseService<SplitBillGroupMember, SplitBillGroupMemberDTO,SplitBillGroupMemberDTO> {

    private final SplitBillGroupMemberRepo repo;
    private final SplitBillGroupMembersMapper mapper;

    public SplitBillGroupMemberService(SplitBillGroupMemberRepo repo, SplitBillGroupMembersMapper mapper) {
        super(repo, mapper);
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<SplitBillGroupMember> findByGroupId(Long groupId){
        SearchRequest searchRequest = new SearchRequest(
                "group",
                Operator.EQUALITY,
                groupId.toString(),
                null
        );

        Specification<SplitBillGroupMember> specification = new SplitBillGroupMemberSpecificationBuilder(
                List.of(searchRequest)
        ).build();

        return this.findAll(specification);
    }

    public void saveAll(List<SplitBillGroupMember> memberList){
        this.repo.saveAll(memberList);
    }
}
