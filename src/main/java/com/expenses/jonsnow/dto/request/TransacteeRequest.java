package com.expenses.jonsnow.dto.request;

import com.expenses.jonsnow.model.User;
import lombok.Data;


@Data
public class TransacteeRequest {
    private String name;
    private String description;
}
