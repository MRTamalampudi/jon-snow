package com.expenses.jonsnow.exceptions;

public class NoSuchEntityException extends Exception{
    public NoSuchEntityException(){
        super("NO such entity found in DB");
    }
}
