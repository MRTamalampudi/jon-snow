package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.config.URLConstants;
import com.expenses.jonsnow.dto.SplitBillGroupDTO;
import com.expenses.jonsnow.mapper.BaseMapper;
import com.expenses.jonsnow.mapper.SplitBillGroupMapper;
import com.expenses.jonsnow.model.*;
import com.expenses.jonsnow.repository.UserRepo;
import com.expenses.jonsnow.service.BaseService;
import com.expenses.jonsnow.service.SplitBillGroupsService;
import com.expenses.jonsnow.service.SplitBillService;
import com.expenses.jonsnow.specification.Builder.SplitBillGroupSpecificationBuilder;
import com.expenses.jonsnow.specification.Builder.UserSpecificationBuilder;
import com.expenses.jonsnow.specification.Operator;
import com.expenses.jonsnow.specification.SearchRequest;
import com.expenses.jonsnow.specification.SplitBillGroupSpecification;
import com.expenses.jonsnow.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = URLConstants.SPLIT_BILL_GROUPS)
public class SplitBillGroupsController extends BaseController<SplitBillGroup, SplitBillGroupDTO,SplitBillGroupDTO>{

    private final SplitBillGroupsService service;
    private final SplitBillGroupMapper mapper;
    private final UserRepo userRepo;
    private final static SplitBillGroupSpecificationBuilder builder =
            new SplitBillGroupSpecificationBuilder(null);
    public SplitBillGroupsController(SplitBillGroupsService service, SplitBillGroupMapper mapper,UserRepo userRepo,SplitBillGroupMapper mapper) {
        super(service, mapper, builder);
        this.service = service;
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

    @Override
    public SplitBillGroupDTO create(SplitBillGroupDTO splitBillGroupDTO) {
        List<String> emails = splitBillGroupDTO.getMemberList().stream().map(groupMember->groupMember.getMember().getEmail()).toList();
        List<SearchRequest> requests = new ArrayList<>(1);
        requests.add(new SearchRequest("email",Operator.IN,null,emails));
        Specification<User> userSpecification = new UserSpecificationBuilder(requests).build();
        List<User> users = userRepo.findAll(userSpecification);
        SplitBillGroup group = mapper.mapRequestToEntity(splitBillGroupDTO);
        List<SplitBillGroupMember> memberList = users.stream().map(user -> {
            SplitBillGroupMember member = new SplitBillGroupMember();
            member.setMember(user);
            member.setGroup(group);
            return member;
        }).toList();
        group.setMemberList(memberList);
        return mapper.mapEntityToDTO(service.create(group));
    }
}
