package com.expenses.jonsnow.aop;

import com.expenses.jonsnow.dto.request.SplitBillRequest;
import com.expenses.jonsnow.model.SplitBill;
import com.expenses.jonsnow.model.SplitBillGroupMember;
import com.expenses.jonsnow.model.SplitBillShare;
import com.expenses.jonsnow.repository.SplitBillRepo;
import com.expenses.jonsnow.service.SplitBillGroupMemberService;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Aspect
@Component
@Log4j2
public class SplitBillAOP {

    public static final String CREATE_BILL_POINTCUT = "execution(* com.expenses.jonsnow.service.SplitBillService.create(..))";
    public static final String UPDATE_BILL_POINTCUT = "execution(* com.expenses.jonsnow.service.SplitBillService.update(..)) && args(splitBillRequest)";
    public static final String DELETE_BILL_POINTCUT = "execution(* com.expenses.jonsnow.service.SplitBillService.deleteAllById(..)) && args(entityIds)";
    private final SplitBillGroupMemberService groupMemberService;
    private final SplitBillRepo repo;

    public SplitBillAOP(
            SplitBillGroupMemberService groupMemberService,
            SplitBillRepo repo
    ) {
        this.groupMemberService = groupMemberService;
        this.repo = repo;
    }

    @AfterReturning(
            value = CREATE_BILL_POINTCUT,
            returning = "splitBill"
    )
    public void doAfterReturningCreateBill(SplitBill splitBill) {
        List<SplitBillGroupMember> memberList = groupMemberService.findByGroupId(splitBill.getSplitBillGroupId());
        updateShares(splitBill,Long::sum,memberList);
        groupMemberService.saveAll(memberList);
    }

    @Around(UPDATE_BILL_POINTCUT)
    public void aroundUpdatingBill(
            ProceedingJoinPoint proceedingJoinPoint,
            SplitBillRequest splitBillRequest
    ) throws Throwable {
        Optional<SplitBill> splitBill = this.repo.findById(splitBillRequest.getId());
        List<SplitBillGroupMember> memberList = groupMemberService.findByGroupId(splitBill.get().getSplitBillGroupId());
        updateShares(
                splitBill.get(),
                (a,b)->a-b,
                memberList
        );
        proceedingJoinPoint.proceed();
        updateShares(
                splitBill.get()
                ,Long::sum,
                memberList
        );
        groupMemberService.saveAll(memberList);
    }

    @Before(DELETE_BILL_POINTCUT)
    public void doBeforeDeleting(List<Long> entityIds) {
        List<SplitBill> splitBills = repo.findAllById(entityIds);
        Long groupId = splitBills.stream().findFirst().map(SplitBill::getSplitBillGroupId).get();
        List<SplitBillGroupMember> memberList= groupMemberService.findByGroupId(groupId);

        splitBills.forEach(splitBill -> {
            updateShares(
                    splitBill,
                    (a,b)->a-b,
                    memberList
            );
        });
    }

    private void updateShares(
            SplitBill splitBill,
            BiFunction<Long,Long,Long> arithmeticFunc,
            List<SplitBillGroupMember> memberList
    ) {
        Predicate<SplitBillShare> filterPendingBills = splitBillShare ->
                splitBillShare.getStatus().isPending() || splitBillShare.getStatus().isRequestedClearence();

        Long lentAmount = splitBill
                .getSplitBillShareList()
                .stream()
                .filter(filterPendingBills)
                .map(SplitBillShare::getAmount)
                .mapToLong(Long::longValue)
                .sum();

        Consumer<SplitBillShare> shareConsumer = splitBillShare -> {
            for(SplitBillGroupMember groupMember:memberList){
                if(!groupMember.getUser().getId().equals(splitBillShare.getUserId())){
                    continue;
                }
                if(splitBillShare.getStatus().isPending() || splitBillShare.getStatus().isRequestedClearence()){
                    groupMember.setOweShare(
                                    arithmeticFunc.apply(
                                            groupMember.getOweShare(),
                                            splitBillShare.getAmount()
                                    )
                    );
                    break;
                }
                if(splitBillShare.getStatus().isPaid()){
                    groupMember.setLentShare(
                            arithmeticFunc.apply(
                                    groupMember.getLentShare(),
                                    lentAmount
                            )
                    );
                    break;
                }
            }
        };

        splitBill.getSplitBillShareList().forEach(shareConsumer);
    }
}
