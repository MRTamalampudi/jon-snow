package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.config.URLConstants;
import com.expenses.jonsnow.dto.SplitBillGroupMemberDTO;
import com.expenses.jonsnow.mapper.BaseMapper;
import com.expenses.jonsnow.mapper.SplitBillGroupMembersMapper;
import com.expenses.jonsnow.model.AuditorAwareImpl;
import com.expenses.jonsnow.model.SplitBillGroupMember;
import com.expenses.jonsnow.service.BaseService;
import com.expenses.jonsnow.service.SplitBillGroupMemberService;
import com.expenses.jonsnow.specification.Builder.BaseSpecificationBuilder;
import com.expenses.jonsnow.specification.Builder.SplitBillGroupMemberSpecificationBuilder;
import com.expenses.jonsnow.specification.Operator;
import com.expenses.jonsnow.specification.SearchRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = URLConstants.SPLIT_BILL_GROUP_MEMEBERS)
@Log4j2
public class SplitBillGroupMemberController extends BaseController<SplitBillGroupMember, SplitBillGroupMemberDTO,SplitBillGroupMemberDTO>{

    public SplitBillGroupMemberController(SplitBillGroupMemberService service, SplitBillGroupMembersMapper mapper) {
        super(service, mapper);
        this.specificationBuilder = new SplitBillGroupMemberSpecificationBuilder(null);
    }

    @Override
    public Page<SplitBillGroupMemberDTO> index(List<SearchRequest> requests, Pageable pageable) {
        requests.add(
                new SearchRequest(
                        "member",
                        Operator.EQUALITY,
                        new AuditorAwareImpl().getCurrentAuditor().get().getId().toString(),
                        null
                )
        );
        log.info("working");
        log.warn("worrr");
        log.error("errr");
        return super.index(requests, pageable);
    }
}
