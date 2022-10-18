package com.bankingsystem.ironhackproject.controller.accounts_controller;

import com.bankingsystem.ironhackproject.model.accounts.Account;
import com.bankingsystem.ironhackproject.model.accounts.dto.DepositDto;
import com.bankingsystem.ironhackproject.model.accounts.dto.TransferDto;
import com.bankingsystem.ironhackproject.service.accounts_service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;
    @PreAuthorize("#accountId == principal.user.userId or hasRole('ROLE_ADMIN')")
    @GetMapping("/account/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccountsByUserId(@PathVariable(value="userId") Integer userId) {
        return accountService.getAllAccountsByUserId(userId);
    }
    @PreAuthorize("#accountId == principal.user.userId or hasRole('ROLE_ADMIN')")
    @PatchMapping("/account/transfer/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean transferMoney(@PathVariable(value="accountId") Integer accountId, @RequestBody TransferDto request) {
        return accountService.transferMoney(request.getSenderAccountId(), request.getReceiverName(), request.getReceiverAccountId(), request.getAmount());
    }

}
