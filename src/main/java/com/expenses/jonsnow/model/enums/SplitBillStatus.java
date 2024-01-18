package com.expenses.jonsnow.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SplitBillStatus {

    PAID(
            0,
            "Paid",
            "Paid Bill on behalf of the group"),
    CLEARED(
            1,
            "Cleared",
            "SplitBill share is paid"),
    REQUESTED_CLEARENCE(
            2,
            "Requested clearence",
            "SplitBill share is paid," +
                    "waiting for approval to get cleared by the member who paid bill"),
    PENDING(
            3,
            "Pending",
            "SplitShare is yet to pay");

    private final Integer id;
    private final String name;
    private final String description;

}
