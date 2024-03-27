package com.expenses.jonsnow.aop;

import com.expenses.jonsnow.model.SplitBill;
import com.expenses.jonsnow.model.SplitBillShare;
import com.expenses.jonsnow.model.TransactionSummary;
import com.expenses.jonsnow.model.enums.SplitBillStatus;
import com.expenses.jonsnow.service.TransactionSummaryService;
import com.expenses.jonsnow.specification.Operator;
import com.expenses.jonsnow.specification.SearchRequest;
import com.expenses.jonsnow.specification.TransactionSummarySpecificationBuilder;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Aspect
@Component
@Log4j2
public class SplitBillAOP {

    public static final String CREATE_BILL_POINTCUT = "execution(* com.expenses.jonsnow.service.SplitBillService.create(..)) && args(bill)";

    private final TransactionSummaryService transactionSummaryService;
    public SplitBillAOP(TransactionSummaryService transactionSummaryService) {
        this.transactionSummaryService = transactionSummaryService;
    }

//    @Around(CREATE_BILL_POINTCUT)
//    public void createBill(ProceedingJoinPoint joinPoint, SplitBill bill) throws Throwable {
//        joinPoint.proceed();
//        log.info(bill.getBill());
//    }

    private void updateTransactionSummary(SplitBill splitBill, SplitBillShare splitBillShare, Long userId) {
        List<SearchRequest> requests = new ArrayList<>(1);
        requests.add(new SearchRequest("user", Operator.EQUALITY, userId.toString(),null));
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
            transactionSummaryService.create(sumary);
        });
    }
}
