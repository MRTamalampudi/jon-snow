package com.expenses.jonsnow.aop;

import com.expenses.jonsnow.dto.request.TransactionRequest;
import com.expenses.jonsnow.model.Transaction;
import com.expenses.jonsnow.model.TransactionSummary;
import com.expenses.jonsnow.model.enums.TransactionType;
import com.expenses.jonsnow.pojo.TransactionSumm;
import com.expenses.jonsnow.repository.TransactionRepo;
import com.expenses.jonsnow.service.TransactionSummaryService;
import com.expenses.jonsnow.service.UserContext;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;


@Aspect
@Log4j2
@Component
public class TransactionAOP {

    public static final String CREATE_POINTCUT = "execution(* com.expenses.jonsnow.service.TransactionService.create(..))";
    public static final String UPDATE_POINTCUT = "execution(* com.expenses.jonsnow.service.TransactionService.update(..)) && args(transactionRequest)";
    public static final String DELETE_POINTCUT = "execution(* com.expenses.jonsnow.service.TransactionService.deleteAllById(..)) && args(entityIds)";
    private final TransactionSummaryService summaryService;
    private final TransactionRepo transactionRepo;

    public TransactionAOP(TransactionSummaryService summaryService, TransactionRepo transactionRepo) {
        this.summaryService = summaryService;
        this.transactionRepo = transactionRepo;
    }

    @AfterReturning(
            value = CREATE_POINTCUT,
            returning = "transaction"
    )
    public void afterRetuningNewTransaction(Transaction transaction){
        TransactionSummary summary = summaryService.getSummary();
        updateSummary(
                Math::addExact,
                transaction.getAmount(),
                transaction.getType(),
                summary
        );
        summaryService.create(summary);
    }

    @Around(UPDATE_POINTCUT)
    public void aroundUpdateTransaction(
            ProceedingJoinPoint proceedingJoinPoint,
            TransactionRequest transactionRequest
    ) throws Throwable {
        TransactionSummary summary = summaryService.getSummary();
        Optional<Transaction> transaction = transactionRepo.findById(transactionRequest.getId());
        transaction.ifPresent(t ->{
            updateSummary(
                    Math::subtractExact,
                    t.getAmount(),
                    t.getType(),
                    summary
            );
        });
        proceedingJoinPoint.proceed();
        updateSummary(
                Math::addExact,
                transactionRequest.getAmount(),
                Objects.requireNonNull(TransactionType.fromString(transactionRequest.getType())),
                summary
        );
        summaryService.create(summary);
    }

    @Before(DELETE_POINTCUT)
    public void beforeDeletingTransaction(List<Long> entityIds){
        List<Transaction> transactions = transactionRepo.findAllById(entityIds);
        TransactionSummary summary = summaryService.getSummary();
        transactions.forEach(transaction -> {
            updateSummary(
                    Math::subtractExact,
                    transaction.getAmount(),
                    transaction.getType(),
                    summary
            );
        });
        summaryService.create(summary);
    }


    private void updateSummary(
            BiFunction<Long,Long,Long> arithmeticFunc,
            Long amount,
            TransactionType transactionType,
            TransactionSummary summary
    ) {
        switch (transactionType){
            case CASH_IN -> summary.setCashIn(
                    arithmeticFunc.apply(
                            summary.getCashIn(),
                            amount
                    )
            );
            case CASH_OUT -> summary.setCashOut(
                    arithmeticFunc.apply(
                            summary.getCashOut(),
                            amount
                    )
            );
            case LENT -> summary.setLent(
                    arithmeticFunc.apply(
                            summary.getLent(),
                            amount
                    )
            );
            case OWE -> summary.setOwe(
                    arithmeticFunc.apply(
                            summary.getOwe(),
                            amount
                    )
            );
        }
    }

    private void updateTransactionSummary() {
        Long userId = UserContext.getUser().getId();
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
