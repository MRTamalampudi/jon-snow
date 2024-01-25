package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.SplitBillShareDTO;
import com.expenses.jonsnow.mapper.BaseMapper;
import com.expenses.jonsnow.mapper.SplitBillShareMapper;
import com.expenses.jonsnow.model.SplitBillShare;
import com.expenses.jonsnow.repository.BaseRepo;
import com.expenses.jonsnow.repository.SplitBillShareRepo;
import org.springframework.stereotype.Service;

@Service
public class SplitBillShareService extends BaseService<SplitBillShare, SplitBillShareDTO,SplitBillShareDTO> {
    public SplitBillShareService(SplitBillShareRepo repo, SplitBillShareMapper mapper) {
        super(repo, mapper);
    }
}
