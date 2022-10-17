package com.bankingsystem.ironhackproject.service.accounts_service;

import com.bankingsystem.ironhackproject.model.accounts.dto.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.Checking;
import com.bankingsystem.ironhackproject.model.utils.Money;

public interface CheckingService {
    Checking findCheckingByAccountId(Integer accountId);
    Checking saveNewChecking(Checking checking);

    Checking updateCheckingBalance(Integer accountId, AccountBalanceUpdateDto balance);

    Checking increaseCheckingBalance(Integer accountId, AccountBalanceUpdateDto balance);
    boolean withdrawalChecking(Integer accountId, Money amount, Integer secretKey);
}
