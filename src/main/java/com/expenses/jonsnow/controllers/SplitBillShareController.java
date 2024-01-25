package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.config.URLConstants;
import com.expenses.jonsnow.dto.SplitBillShareDTO;
import com.expenses.jonsnow.mapper.SplitBillShareMapper;
import com.expenses.jonsnow.model.AuditorAwareImpl;
import com.expenses.jonsnow.model.SplitBillShare;
import com.expenses.jonsnow.service.SplitBillShareService;
import com.expenses.jonsnow.specification.Operator;
import com.expenses.jonsnow.specification.SearchRequest;
import com.expenses.jonsnow.specification.Builder.SplitBillShareSpecificationBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(URLConstants.SPLIT_BILL_SHARE)
public class SplitBillShareController extends BaseController<SplitBillShare, SplitBillShareDTO,SplitBillShareDTO> {

    public static final SplitBillShareSpecificationBuilder builder =
            new SplitBillShareSpecificationBuilder(null);

    public SplitBillShareController(SplitBillShareService service, SplitBillShareMapper mapper) {
        super(service, mapper, builder);
    }

    @Override
    public Page<SplitBillShareDTO> index(List<SearchRequest> requests, Pageable pageable) {
        requests.add(new SearchRequest(
                "user",
                Operator.EQUALITY,
                new AuditorAwareImpl().getCurrentAuditor().get().getId().toString(),
                null
        ));
        return super.index(requests, pageable);
    }
}
