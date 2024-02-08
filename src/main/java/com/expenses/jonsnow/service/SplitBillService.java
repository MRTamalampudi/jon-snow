package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.SplitBillDTO;
import com.expenses.jonsnow.dto.request.SplitBillRequest;
import com.expenses.jonsnow.mapper.BaseMapper;
import com.expenses.jonsnow.mapper.SplitBillMapper;
import com.expenses.jonsnow.model.SplitBill;
import com.expenses.jonsnow.model.SplitBillGroupMember;
import com.expenses.jonsnow.model.SplitBillShare;
import com.expenses.jonsnow.model.TransactionSummary;
import com.expenses.jonsnow.model.enums.SplitBillStatus;
import com.expenses.jonsnow.repository.BaseRepo;
import com.expenses.jonsnow.repository.SplitBillRepo;
import com.expenses.jonsnow.specification.*;
import com.expenses.jonsnow.specification.Builder.SplitBillGroupMemberSpecificationBuilder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SplitBillService extends BaseService<SplitBill, SplitBillDTO, SplitBillRequest> {

    private final SplitBillGroupMemberService groupMemberService;
    private final TransactionSummaryService transactionSummaryService;
    public SplitBillService(
            SplitBillRepo repo,
            SplitBillMapper mapper,
            SplitBillGroupMemberService groupMemberService,
            TransactionSummaryService transactionSummaryService
    ) {
        super(repo, mapper);
        this.groupMemberService = groupMemberService;
        this.transactionSummaryService = transactionSummaryService;
    }

    @Override
    @Transactional
    public SplitBill create(SplitBill splitBill) {
        for (SplitBillShare splitBillShare : splitBill.getSplitBillShareList()) {
            Long groupId = splitBill.getSplitBillGroupId();
            Long userId = splitBillShare.getUserId();

            updateTransactionSummary(splitBill, splitBillShare, userId);
            updateGroupMembersShare(splitBill, splitBillShare, groupId, userId);
        }
        return super.create(splitBill);
    }

    private void updateTransactionSummary(SplitBill splitBill, SplitBillShare splitBillShare, Long userId) {
        List<SearchRequest> requests = new ArrayList<>(1);
        requests.add(new SearchRequest("user",Operator.EQUALITY, userId.toString(),null));
        Specification<TransactionSummary> specification = new TransactionSummarySpecificationBuilder(requests).build();
        Optional<TransactionSummary> summary = transactionSummaryService.findAll(specification).stream().findFirst();
        Long amount = splitBillShare.getAmount();
        Long billAmount = splitBill.getAmount();
        summary.ifPresent(sumary->{
            if(splitBillShare.getStatus() == SplitBillStatus.PAID){
                sumary.setLent(sumary.getLent() + (billAmount - amount));
            } else {
                sumary.setOwe(sumary.getOwe() + amount);
            }
            transactionSummaryService.update(sumary);
        });
    }

    private void updateGroupMembersShare(SplitBill splitBill, SplitBillShare splitBillShare, Long groupId, Long userId) {
        List<SearchRequest> requests = new ArrayList<>(2);
        requests.add(new SearchRequest("group",Operator.EQUALITY, groupId.toString(),null));
        requests.add(new SearchRequest("member",Operator.EQUALITY, userId.toString(),null));
        Specification<SplitBillGroupMember> specification = new SplitBillGroupMemberSpecificationBuilder(requests).build();
        Optional<SplitBillGroupMember> memberOptional = groupMemberService.findAll(specification).stream().findFirst();

        memberOptional.ifPresent(member -> {
            Long amount = splitBillShare.getAmount();
            Long billAmount = splitBill.getAmount();

            if (splitBillShare.getStatus() == SplitBillStatus.PAID) {
                member.setLentShare(member.getLentShare() + (billAmount - amount));
            } else {
                member.setOweShare(member.getOweShare() + amount);
            }
            groupMemberService.update(member);
        });
    }
}
