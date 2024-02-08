package com.expenses.jonsnow.dto.request;

import com.expenses.jonsnow.model.enums.SplitBillStatus;
import lombok.Data;

@Data
public class SplitBillShareRequest {
    private Long amount;
    private Long userId;
    private SplitBillStatus status;
}
