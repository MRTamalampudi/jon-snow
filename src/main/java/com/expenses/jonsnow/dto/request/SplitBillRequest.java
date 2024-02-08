package com.expenses.jonsnow.dto.request;

import com.expenses.jonsnow.dto.UserDTO;
import com.expenses.jonsnow.model.enums.SplitAlgo;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class SplitBillRequest {
    private Long id;
    private String bill;
    private Long amount;
    private SplitAlgo splitAlgo;
    private Instant date;
    private Long paidUserId;
    private Long splitBillGroupId;
    private List<SplitBillShareRequest> splitBillShareList;
}
