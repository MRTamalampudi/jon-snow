package com.expenses.jonsnow.dto;

import com.expenses.jonsnow.model.User;
import lombok.Data;

@Data
public class TransacteeDTO {
    private Long id;
    private String name;
    private String description;
}
