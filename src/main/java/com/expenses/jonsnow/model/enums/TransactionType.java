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

    private final String type;

    public static TransactionType fromString(String text) {
        for (TransactionType type : TransactionType.values()) {
            if (type.type.equalsIgnoreCase(text)) {
                return type;
            }
        }
        return null;
    }



}