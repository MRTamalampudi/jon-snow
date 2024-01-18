package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.config.URLConstants;
import com.expenses.jonsnow.dto.SplitBillGroupDTO;
import com.expenses.jonsnow.mapper.BaseMapper;
import com.expenses.jonsnow.model.AuditorAwareImpl;
import com.expenses.jonsnow.model.SplitBillGroup;
import com.expenses.jonsnow.model.SplitBillGroupMember;
import com.expenses.jonsnow.service.BaseService;
import com.expenses.jonsnow.specification.Builder.SplitBillGroupSpecificationBuilder;
import com.expenses.jonsnow.specification.Operator;
import com.expenses.jonsnow.specification.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = URLConstants.SPLIT_BILL_GROUPS)
public class SplitBillGroupsController extends BaseController<SplitBillGroup, SplitBillGroupDTO,SplitBillGroupDTO>{
    
    public SplitBillGroupsController(BaseService<SplitBillGroup, SplitBillGroupDTO, SplitBillGroupDTO> service, BaseMapper<SplitBillGroup, SplitBillGroupDTO, SplitBillGroupDTO> mapper) {
        super(service, mapper);
        this.specificationBuilder = new SplitBillGroupSpecificationBuilder(null);
    }
}
