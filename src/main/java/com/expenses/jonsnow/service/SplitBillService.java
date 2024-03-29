package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.SplitBillDTO;
import com.expenses.jonsnow.dto.request.SplitBillRequest;
import com.expenses.jonsnow.dto.request.SplitBillShareRequest;
import com.expenses.jonsnow.mapper.BaseMapper;
import com.expenses.jonsnow.mapper.SplitBillMapper;
import com.expenses.jonsnow.mapper.SplitBillShareMapper;
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
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Log4j2
public class SplitBillService extends BaseService<SplitBill, SplitBillDTO, SplitBillRequest> {

    private final SplitBillGroupMemberService groupMemberService;
    private final TransactionSummaryService transactionSummaryService;
    private final SplitBillMapper mapper;

    private final SplitBillRepo repo ;
    private final SplitBillShareMapper shareMapper;
    private final SplitBillShareService splitBillShareService;
    public SplitBillService(
            SplitBillRepo repo,
            SplitBillMapper mapper,
            SplitBillGroupMemberService groupMemberService,
            TransactionSummaryService transactionSummaryService,
            SplitBillShareMapper shareMapper,
            SplitBillShareService splitBillShareService
    ) {
        super(repo, mapper);
        this.groupMemberService = groupMemberService;
        this.transactionSummaryService = transactionSummaryService;
        this.repo = repo;
        this.shareMapper = shareMapper;
        this.mapper = mapper;
        this.splitBillShareService = splitBillShareService;
    }

    @Override
    @Transactional
    public SplitBill create(SplitBill splitBill) {
        return super.create(splitBill);
    }

    @Override
    public Optional<SplitBill> update(SplitBillRequest splitBillRequest) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Optional<SplitBill> splitBill = this.repo.findById(splitBillRequest.getId());
        splitBill.ifPresent(entity->this.mapper.mapRequestToEntity(splitBillRequest,entity));
        splitBill.ifPresent(
                entity -> entity
                        .getSplitBillShareList()
                        .forEach(
                                splitBillShare -> splitBillShare.setBill(splitBill.get())
                        )
        );
        splitBill.ifPresent(this::create);
        return splitBill;
    }
}
