package com.bankingsystem.ironhackproject.controller.accounts_controller;

import com.bankingsystem.ironhackproject.model.accounts.dto.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.Savings;
import com.bankingsystem.ironhackproject.service.accounts_service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class SavingsController {
    @Autowired
    SavingsService savingsService;

    @PreAuthorize("#accountId == principal.user.userId or hasRole('ROLE_ADMIN')")
    @GetMapping("/account/savings/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    Savings getSavings(@PathVariable(value="accountId") Integer accountId) {
        return savingsService.findSavingsByAccountId(accountId);
    }

    @PatchMapping("/savings/{accountId}/balance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Savings updateSavingsBalance(@PathVariable(value="accountId")Integer accountId, @RequestBody AccountBalanceUpdateDto balance) {
        return savingsService.updateSavingsBalance(accountId,balance);
    }

}