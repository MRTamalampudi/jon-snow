package com.expenses.jonsnow.pojo;

import com.expenses.jonsnow.model.enums.TransactionType;

public interface TransactionSumm{
    TransactionType getType();
    Long getAmount();
}
