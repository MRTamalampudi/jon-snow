package com.expenses.jonsnow.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentMode {
    UPI("UPI"),
    CASH("Cash"),
    NET_BANKING("Net Banking"),
    DEBIT_CARD("Debit Card"),
    CRYPTO_CURRENCY("Crypto Currency"),
    CREDIT_CARD("Credit Card"),
    OTHER("Other");

    private String mode;

    public String getMode() {
        return mode;
    }

    public static PaymentMode fromString(String text) {
        for (PaymentMode mode : PaymentMode.values()) {
            if (mode.mode.equalsIgnoreCase(text)) {
                return mode;
            }
        }
        return null; // Or throw an IllegalArgumentException
    }
}
