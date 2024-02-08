package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.config.URLConstants;
import com.expenses.jonsnow.dto.SplitBillDTO;
import com.expenses.jonsnow.dto.request.SplitBillRequest;
import com.expenses.jonsnow.mapper.BaseMapper;
import com.expenses.jonsnow.mapper.SplitBillMapper;
import com.expenses.jonsnow.model.SplitBill;
import com.expenses.jonsnow.service.BaseService;
import com.expenses.jonsnow.service.SplitBillService;
import com.expenses.jonsnow.specification.Builder.BaseSpecificationBuilder;
import com.expenses.jonsnow.specification.Builder.SplitBillSpecificationBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = URLConstants.SPLIT_BILL)
public class SplitBillController extends BaseController<SplitBill, SplitBillDTO, SplitBillRequest> {

    private static final SplitBillSpecificationBuilder builder =
            new SplitBillSpecificationBuilder(null);

    public SplitBillController(SplitBillService service, SplitBillMapper mapper) {
        super(service, mapper, builder);
    }
}
