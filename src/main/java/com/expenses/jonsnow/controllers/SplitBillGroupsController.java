package com.expenses.jonsnow.controllers;

import com.expenses.jonsnow.config.URLConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = URLConstants.SPLIT_BILL_GROUPS)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SplitBillGroupsController {

}
