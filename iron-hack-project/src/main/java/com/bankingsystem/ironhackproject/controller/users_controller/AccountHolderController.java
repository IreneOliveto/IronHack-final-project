package com.bankingsystem.ironhackproject.controller.users_controller;

import com.bankingsystem.ironhackproject.model.users.AccountHolder;
import com.bankingsystem.ironhackproject.service.users_service.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountHolderController {

    @Autowired
    AccountHolderService accountHolderService;

    @PreAuthorize("#accountId == principal.user.userId or hasRole('ROLE_ADMIN')")
    @GetMapping("/account-holder/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountHolder getAccountHolder (@PathVariable(value="accountId") Integer accountId) {
        return accountHolderService.getAccountHolderById(accountId);
    }

    @PostMapping("/account-holder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder saveNewAccountHolder(@Valid @RequestBody AccountHolder accountHolder) {
        return accountHolderService.saveNewAccountHolder(accountHolder);
    }
}
