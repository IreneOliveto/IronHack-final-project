package com.bankingsystem.ironhackproject.controller.accounts_controller;

import com.bankingsystem.ironhackproject.model.accounts.dto.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.Checking;
import com.bankingsystem.ironhackproject.model.accounts.StudentChecking;
import com.bankingsystem.ironhackproject.model.accounts.dto.DepositDto;
import com.bankingsystem.ironhackproject.model.users.AccountHolder;
import com.bankingsystem.ironhackproject.service.accounts_service.CheckingService;
import com.bankingsystem.ironhackproject.service.accounts_service.StudentCheckingService;
import com.bankingsystem.ironhackproject.service.users_service.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CheckingController {
    @Autowired
    CheckingService checkingService;

    @Autowired
    StudentCheckingService studentCheckingService;
    @Autowired
    AccountHolderService accountHolderService;

    @PreAuthorize("#accountId == principal.user.userId or hasRole('ROLE_ADMIN')")
    @GetMapping("/account/checking/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Checking getChecking(@PathVariable(value="accountId") Integer accountId) {
        return checkingService.findCheckingByAccountId(accountId);
    }

    @PatchMapping("/checking/{accountId}/balance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Checking updateCheckingBalance(@PathVariable(value="accountId")Integer accountId, @RequestBody AccountBalanceUpdateDto balance) {
        return checkingService.updateCheckingBalance(accountId,balance);
    }
    @PreAuthorize("#accountId == principal.user.userId or hasRole('ROLE_ADMIN')")
    @PatchMapping("/checking/{accountId}/deposit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Checking increaseCheckingBalance(@PathVariable(value="accountId")Integer accountId, @RequestBody AccountBalanceUpdateDto balance) {
        return checkingService.increaseCheckingBalance(accountId,balance);
    }

    @PatchMapping("/checking/{accountId}/withdrawal")
    @ResponseStatus(HttpStatus.OK)
    public boolean withdrawal(@PathVariable(value="accountId")Integer accountId, @RequestHeader Integer hashedKey, @RequestBody DepositDto request) {
        if(request.getSecretKey() == getChecking(accountId).getSecretKey()) {
            return checkingService.withdrawalChecking(request.getReceiverAccountId(), request.getAmount(), request.getSecretKey());
        } else if(request.getSecretKey() != getChecking(accountId).getSecretKey()) {
            throw new IllegalArgumentException("This secret key is not correct.");
        }
        return true;
    }

    @PostMapping("/checking")
    @ResponseStatus(HttpStatus.CREATED)
    public Checking saveChecking(@Valid @RequestBody Integer accountHolderId) {
        AccountHolder accountHolder = accountHolderService.getAccountHolderById(accountHolderId);
        if(accountHolder != null) {
            if(accountHolderService.isAccountHolderOlderThanTwentyFour(accountHolderId)) {
                return checkingService.saveNewChecking(new Checking(accountHolder));
            }
            return studentCheckingService.saveNewStudentChecking(new StudentChecking(accountHolder));
        }else return null;

    }
}
