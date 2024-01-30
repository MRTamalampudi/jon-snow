package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.config.URLConstants;
import com.expenses.jonsnow.dto.SplitBillGroupDTO;
import com.expenses.jonsnow.mapper.SplitBillGroupMapper;
import com.expenses.jonsnow.model.SplitBillGroup;
import com.expenses.jonsnow.service.SplitBillGroupsService;
import com.expenses.jonsnow.specification.Builder.SplitBillGroupSpecificationBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = URLConstants.SPLIT_BILL_GROUPS)
public class SplitBillGroupsController extends BaseController<SplitBillGroup, SplitBillGroupDTO,SplitBillGroupDTO>{
    private final static SplitBillGroupSpecificationBuilder builder =
            new SplitBillGroupSpecificationBuilder(null);
    public SplitBillGroupsController(
            SplitBillGroupsService service,
            SplitBillGroupMapper mapper
    ) {
        super(service, mapper, builder);
    }
}
