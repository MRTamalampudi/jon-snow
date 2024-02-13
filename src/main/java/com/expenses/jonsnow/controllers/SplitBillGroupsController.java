package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.config.URLConstants;
import com.expenses.jonsnow.dto.SplitBillGroupDTO;
import com.expenses.jonsnow.exceptions.NoSuchEntityException;
import com.expenses.jonsnow.mapper.SplitBillGroupMapper;
import com.expenses.jonsnow.model.SplitBillGroup;
import com.expenses.jonsnow.service.SplitBillGroupsService;
import com.expenses.jonsnow.specification.Builder.SplitBillGroupSpecificationBuilder;
import com.expenses.jonsnow.specification.Operator;
import com.expenses.jonsnow.specification.SearchRequest;
import com.expenses.jonsnow.specification.SplitBillGroupSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = URLConstants.SPLIT_BILL_GROUPS)
public class SplitBillGroupsController extends BaseController<SplitBillGroup, SplitBillGroupDTO,SplitBillGroupDTO>{
    private final static SplitBillGroupSpecificationBuilder builder =
            new SplitBillGroupSpecificationBuilder(null);
    private final SplitBillGroupsService service;
    private final SplitBillGroupMapper mapper;
    public SplitBillGroupsController(
            SplitBillGroupsService service,
            SplitBillGroupMapper mapper
    ) {
        super(service, mapper, builder);
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public SplitBillGroupDTO get(Long entityId) throws NoSuchEntityException {
        if(Objects.isNull(entityId)){
            SearchRequest request = new SearchRequest("recent", null,null,null);
            Specification<SplitBillGroup> groupSpecification = new SplitBillGroupSpecificationBuilder(List.of(request)).build();
            Optional<SplitBillGroup> group = service.findAll(groupSpecification).stream().findFirst();
            if (group.isPresent())  return mapper.mapEntityToDTO(group.get());
        }
        return super.get(entityId);
    }
}
