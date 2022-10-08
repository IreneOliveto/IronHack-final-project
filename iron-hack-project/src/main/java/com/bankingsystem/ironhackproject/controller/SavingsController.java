package com.bankingsystem.ironhackproject.controller;

import com.bankingsystem.ironhackproject.model.accounts.Savings;
import com.bankingsystem.ironhackproject.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SavingsController {
    @Autowired
    SavingsService savingsService;

    @GetMapping("/savings/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    Savings getSavings(@PathVariable(value="accountId") Integer accountId) {
        return savingsService.findSavingsByAccountId(accountId);
    }


}