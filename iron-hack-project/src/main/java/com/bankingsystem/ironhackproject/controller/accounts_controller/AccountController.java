package com.bankingsystem.ironhackproject.controller.accounts_controller;

import com.bankingsystem.ironhackproject.model.accounts.Checking;
import com.bankingsystem.ironhackproject.model.accounts.TransferDto;
import com.bankingsystem.ironhackproject.service.accounts_service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public class AccountController {

    AccountService accountService;

    @PostMapping("/account/transfer")
    @ResponseStatus(HttpStatus.OK)
    public boolean transferMoney(@RequestBody TransferDto request) {
        return accountService.transferMoney(request.getReceiverName(), request.getReceiverAccountId(), request.getAmount());
    }
}
