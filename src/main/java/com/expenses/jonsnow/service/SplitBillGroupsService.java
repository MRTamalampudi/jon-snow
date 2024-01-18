package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.SplitBillGroupDTO;
import com.expenses.jonsnow.mapper.BaseMapper;
import com.expenses.jonsnow.mapper.SplitBillGroupMapper;
import com.expenses.jonsnow.model.SplitBillGroup;
import com.expenses.jonsnow.repository.BaseRepo;
import com.expenses.jonsnow.repository.SplitBillGroupRepo;
import org.springframework.stereotype.Service;

@Service
public class SplitBillGroupsService extends BaseService<SplitBillGroup, SplitBillGroupDTO,SplitBillGroupDTO> {

    private final SplitBillGroupRepo repo;
    private final SplitBillGroupMapper mapper;

    public SplitBillGroupsService(SplitBillGroupRepo repo,SplitBillGroupMapper mapper) {
        super(repo, mapper);
        this.repo = repo;
        this.mapper = mapper;
    }
}
