package com.bankingsystem.ironhackproject.controller;

import com.bankingsystem.ironhackproject.model.accounts.AccountBalanceUpdateDto;
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
    @ResponseStatus(HttpStatus.OK)
    Checking getChecking(@PathVariable(value="accountId") Integer accountId) {
        return checkingService.findCheckingByAccountId(accountId);
    }

    @PatchMapping("/checking/{accountId}/balance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Checking updateCheckingBalance(@PathVariable(value="accountId")Integer accountId, @RequestBody AccountBalanceUpdateDto balance) {
        return checkingService.updateBalance(accountId,balance);
    }



}
