package com.expenses.jonsnow.specification;

import java.util.List;

public enum Operator {
    EQUALITY,
    LIKE,
    GREATER_THAN,
    LESS_THAN,
    GREATER_THAN_OR_EQUAL_TO,
    LESSER_THAN_OR_EQUAL_TO,
    IN;

    public static final List<String> OPERATOR_SET = List
            .of(":", ">", "<", ">=", "<=", "~", "@");

    public static Operator getOperator(String operatorString){
        return switch (operatorString){
            case ":" -> EQUALITY;
            case ">" -> GREATER_THAN;
            case "<" -> LESS_THAN;
            case ">=" -> GREATER_THAN_OR_EQUAL_TO;
            case "<=" -> LESSER_THAN_OR_EQUAL_TO;
            case "~" -> LIKE;
            case "@" -> IN;
            default -> null;
        };
    }
}
