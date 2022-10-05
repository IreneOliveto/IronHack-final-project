package com.bankingsystem.ironhackproject.controller;

import com.bankingsystem.ironhackproject.model.accounts.Checking;
import com.bankingsystem.ironhackproject.service.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CheckingController {

    @Autowired
    CheckingService checkingService;

    @GetMapping("/checking/{accountId}")
    Checking getChecking(@PathVariable(value="accountId") Integer accountId) {
        return checkingService.findCheckingByAccountId(accountId);
    }

}
