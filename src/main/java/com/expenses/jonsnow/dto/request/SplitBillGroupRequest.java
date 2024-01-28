package com.expenses.jonsnow.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class SplitBillGroupRequest {
    private String name;
    private List<String> membersList;
}
