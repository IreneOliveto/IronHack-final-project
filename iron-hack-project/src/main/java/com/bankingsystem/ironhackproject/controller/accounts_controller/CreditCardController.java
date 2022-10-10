package com.bankingsystem.ironhackproject.controller.accounts_controller;

import com.bankingsystem.ironhackproject.model.accounts.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.CreditCard;
import com.bankingsystem.ironhackproject.service.accounts_service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CreditCardController {
    @Autowired
    CreditCardService creditCardService;

    @GetMapping("/credit-card/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    CreditCard getCreditCard(@PathVariable(value="accountId") Integer accountId) {
        return creditCardService.findCreditCardByAccountId(accountId);
    }

    @PatchMapping("/credit-card/{accountId}/balance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CreditCard updateCreditCardBalance(@PathVariable(value="accountId")Integer accountId, @RequestBody AccountBalanceUpdateDto balance) {
        return creditCardService.updateCreditCardBalance(accountId,balance);
    }
}
