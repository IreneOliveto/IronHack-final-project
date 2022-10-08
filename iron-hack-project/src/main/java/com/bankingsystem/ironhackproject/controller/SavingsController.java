package com.bankingsystem.ironhackproject.controller;

import com.bankingsystem.ironhackproject.model.accounts.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.Savings;
import com.bankingsystem.ironhackproject.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class SavingsController {
    @Autowired
    SavingsService savingsService;

    @GetMapping("/savings/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    Savings getSavings(@PathVariable(value="accountId") Integer accountId) {
        return savingsService.findSavingsByAccountId(accountId);
    }

    @PatchMapping("/credit-card/{accountId}/balance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Savings updateSavingsBalance(@PathVariable(value="accountId")Integer accountId, @RequestBody AccountBalanceUpdateDto balance) {
        return savingsService.updateBalance(accountId,balance);
    }


}