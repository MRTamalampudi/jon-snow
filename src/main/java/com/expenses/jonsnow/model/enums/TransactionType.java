package com.expenses.jonsnow.model.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum TransactionType {
    CASH_IN("Cash in"),
    CASH_OUT("Cash out"),
    OWE("Owe"),
    LENT("Lent");

    private String type;

}