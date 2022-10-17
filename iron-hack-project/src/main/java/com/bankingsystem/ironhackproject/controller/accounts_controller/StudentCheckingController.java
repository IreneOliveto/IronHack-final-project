package com.bankingsystem.ironhackproject.controller.accounts_controller;

import com.bankingsystem.ironhackproject.model.accounts.dto.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.StudentChecking;
import com.bankingsystem.ironhackproject.service.accounts_service.StudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentCheckingController {

    @Autowired
    StudentCheckingService studentCheckingService;

    @PreAuthorize("#accountId == principal.user.userId or hasRole('ROLE_ADMIN')")
    @GetMapping("/account/student/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    StudentChecking getStudentChecking(@PathVariable(value="accountId") Integer accountId) {
        return studentCheckingService.findStudentCheckingByAccountId(accountId);
    }

    @PatchMapping("/student/{accountId}/balance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public StudentChecking updateCreditCardBalance(@PathVariable(value="accountId")Integer accountId, @RequestBody AccountBalanceUpdateDto balance) {
        return studentCheckingService.updateStudentBalance(accountId,balance);
    }
}
