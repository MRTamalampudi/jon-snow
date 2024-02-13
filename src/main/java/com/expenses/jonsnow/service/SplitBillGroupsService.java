package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.SplitBillGroupDTO;
import com.expenses.jonsnow.mapper.SplitBillGroupMapper;
import com.expenses.jonsnow.model.SplitBillGroup;
import com.expenses.jonsnow.model.SplitBillGroupMember;
import com.expenses.jonsnow.model.User;
import com.expenses.jonsnow.repository.SplitBillGroupRepo;
import com.expenses.jonsnow.specification.Operator;
import com.expenses.jonsnow.specification.SearchRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class SplitBillGroupsService extends BaseService<SplitBillGroup, SplitBillGroupDTO,SplitBillGroupDTO> {

    private final SplitBillGroupRepo repo;
    private final SplitBillGroupMapper mapper;

    private final UserService userService;

    private final SplitBillGroupMemberService memberService;

    public SplitBillGroupsService(
            SplitBillGroupRepo repo,
            SplitBillGroupMapper mapper,
            UserService userService,
            SplitBillGroupMemberService memberService
    ) {
        super(repo, mapper);
        this.repo = repo;
        this.mapper = mapper;
        this.userService = userService;
        this.memberService = memberService;
    }

    @Override
    public SplitBillGroup create(SplitBillGroup splitBillGroup) {
        Function<SplitBillGroupMember,String> getGroupMemberEmail = groupMember -> groupMember.getMember().getEmail();
        List<String> emails = splitBillGroup
                .getMemberList()
                .stream()
                .map(getGroupMemberEmail)
                .toList();
        splitBillGroup.setMemberList(null);
        SplitBillGroup finalSplitBillGroup = super.create(splitBillGroup);
        List<User> users = userService.
                findAll(
                        List.of(
                                new SearchRequest(
                                        "email",
                                        Operator.IN,
                                        null, emails
                                )
                        )
                );
        users.add(UserContext.getUser());
        List<SplitBillGroupMember> memberList = users.stream().map(user -> {
            SplitBillGroupMember member = new SplitBillGroupMember();
            member.setMember(user);
            member.setGroup(finalSplitBillGroup);
            return member;
        }).peek(memberService::create).toList();
        splitBillGroup.setMemberList(memberList);
        return splitBillGroup;
    }


}
