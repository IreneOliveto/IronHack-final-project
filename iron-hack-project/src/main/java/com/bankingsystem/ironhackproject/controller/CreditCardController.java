package com.bankingsystem.ironhackproject.controller;

import com.bankingsystem.ironhackproject.model.accounts.CreditCard;
import com.bankingsystem.ironhackproject.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditCardController {
    @Autowired
    CreditCardService creditCardService;

    @GetMapping("/credit-card/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    CreditCard getCreditCard(@PathVariable(value="accountId") Integer accountId) {
        return creditCardService.findCreditCardByAccountId(accountId);
    }
}
