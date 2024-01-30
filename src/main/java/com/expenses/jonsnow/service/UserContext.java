package com.expenses.jonsnow.service;

import com.expenses.jonsnow.model.AuditorAwareImpl;
import com.expenses.jonsnow.model.User;

public class UserContext {
    public static User getUser(){
        return new AuditorAwareImpl().getCurrentAuditor().get();
    }
}
