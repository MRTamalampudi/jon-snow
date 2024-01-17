package com.expenses.jonsnow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ResponseErrorDTO {
    private String error;
    private Integer status;
}
