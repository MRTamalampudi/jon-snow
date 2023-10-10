package com.expenses.jonsnow.specification;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchRequest {
    private String key;
    private Operator operator;
    private String value;
    private List<String> values;
}
