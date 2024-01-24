package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.SplitBillDTO;
import com.expenses.jonsnow.mapper.BaseMapper;
import com.expenses.jonsnow.mapper.SplitBillMapper;
import com.expenses.jonsnow.model.SplitBill;
import com.expenses.jonsnow.repository.BaseRepo;
import com.expenses.jonsnow.repository.SplitBillRepo;
import org.springframework.stereotype.Service;

@Service
public class SplitBillService extends BaseService<SplitBill, SplitBillDTO,SplitBillDTO> {
    public SplitBillService(SplitBillRepo repo, SplitBillMapper mapper) {
        super(repo, mapper);
    }
}
