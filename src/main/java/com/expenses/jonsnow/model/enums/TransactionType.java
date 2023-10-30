package com.expenses.jonsnow.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TransactionType {
    CASH_IN("Cash in"),
    CASH_OUT("Cash out"),
    OWE("Owe"),
    LENT("Lent");

    private final String corr;
}