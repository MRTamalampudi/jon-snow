package com.expenses.jonsnow.aoc;

import com.expenses.jonsnow.model.TransactionSummary;
import com.expenses.jonsnow.pojo.TransactionSumm;
import com.expenses.jonsnow.repository.TransactionRepo;
import com.expenses.jonsnow.service.TransactionSummaryService;
import com.expenses.jonsnow.service.UserContext;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;


@Aspect
@Log4j2
@Component
public class TransactionAOC {

    private final TransactionSummaryService summaryService;
    private final TransactionRepo transactionRepo;

    public TransactionAOC(TransactionSummaryService summaryService,TransactionRepo transactionRepo) {
        this.summaryService = summaryService;
        this.transactionRepo = transactionRepo;
    }

    @After("execution(* com.expenses.jonsnow.service.TransactionService.create(..)) " +
            "|| execution(* com.expenses.jonsnow.service.TransactionService.update(..)) " +
            "|| execution(* com.expenses.jonsnow.service.TransactionService.deleteAllById(..)) ")
    public void doNothing(JoinPoint joinPoint){
      updateTransactionSummary();
    }

    private void updateTransactionSummary() {
        List<TransactionSumm> transactionSumms = transactionRepo.sumAmount(UserContext.getUser().getId());
        TransactionSummary summary = summaryService.getSummary();
        transactionSumms.forEach(transactionSumm -> {
            switch (transactionSumm.getType()){
                case CASH_IN -> summary.setCashIn(transactionSumm.getAmount());
                case CASH_OUT -> summary.setCashOut(transactionSumm.getAmount());
                case LENT -> summary.setLent(transactionSumm.getAmount());
                case OWE -> summary.setOwe(transactionSumm.getAmount());
            }
        });
        summaryService.create(summary);
    }
}
