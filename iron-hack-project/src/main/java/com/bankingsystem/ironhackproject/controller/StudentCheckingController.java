package com.bankingsystem.ironhackproject.controller;

import com.bankingsystem.ironhackproject.model.accounts.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.CreditCard;
import com.bankingsystem.ironhackproject.model.accounts.StudentChecking;
import com.bankingsystem.ironhackproject.service.StudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentCheckingController {

    @Autowired
    StudentCheckingService studentCheckingService;

    @GetMapping("/student/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    StudentChecking getStudentChecking(@PathVariable(value="accountId") Integer accountId) {
        return studentCheckingService.findStudentCheckingByAccountId(accountId);
    }

    @PatchMapping("/credit-card/{accountId}/balance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public StudentChecking updateCreditCardBalance(@PathVariable(value="accountId")Integer accountId, @RequestBody AccountBalanceUpdateDto balance) {
        return studentCheckingService.updateBalance(accountId,balance);
    }
}
