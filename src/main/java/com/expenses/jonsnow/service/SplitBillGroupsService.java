package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.SplitBillGroupDTO;
import com.expenses.jonsnow.exceptions.NoSuchEntityException;
import com.expenses.jonsnow.mapper.SplitBillGroupMapper;
import com.expenses.jonsnow.model.SplitBillGroup;
import com.expenses.jonsnow.model.SplitBillGroupMember;
import com.expenses.jonsnow.model.User;
import com.expenses.jonsnow.repository.SplitBillGroupRepo;
import com.expenses.jonsnow.specification.Operator;
import com.expenses.jonsnow.specification.SearchRequest;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
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
        List<String> emails = this.getEmails(splitBillGroup);
        splitBillGroup.setMemberList(null);
        SplitBillGroup finalSplitBillGroup = super.create(splitBillGroup);
        List<User> users = getAllUsersByEmail(emails);
        users.add(UserContext.getUser());
        List<SplitBillGroupMember> memberList = createSplitBillGroupMembers(users, finalSplitBillGroup);
        splitBillGroup.setMemberList(memberList);
        return splitBillGroup;
    }

    private List<SplitBillGroupMember> createSplitBillGroupMembers(List<User> users, SplitBillGroup finalSplitBillGroup) {
        return users.stream().map(user -> {
            SplitBillGroupMember member = new SplitBillGroupMember();
            member.setMember(user);
            member.setGroup(finalSplitBillGroup);
            return member;
        }).peek(memberService::create).toList();
    }

    private List<User> getAllUsersByEmail(List<String> emails) {
        return userService.
                findAll(
                        List.of(
                                new SearchRequest(
                                        "email",
                                        Operator.IN,
                                        null, emails
                                )
                        )
                );
    }

    private List<String> getEmails(SplitBillGroup splitBillGroup) {
        Function<SplitBillGroupMember,String> getGroupMemberEmail = groupMember -> groupMember.getMember().getEmail();
        List<String> emails = splitBillGroup
                .getMemberList()
                .stream()
                .map(getGroupMemberEmail)
                .toList();
        return emails;
    }

    @Override
    public Optional<SplitBillGroup> update(SplitBillGroupDTO splitBillGroupDTO) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchEntityException {
        SplitBillGroup splitBillGroup = mapper.mapRequestToEntity(splitBillGroupDTO);
        List<String> emails = getEmails(splitBillGroup);
        SplitBillGroup splitBillGroup1 = this.findById(splitBillGroup.getId());
        List<User> users = getAllUsersByEmail(emails);
        createSplitBillGroupMembers(users,splitBillGroup1);
        return Optional.ofNullable(splitBillGroup1);
    }
}
