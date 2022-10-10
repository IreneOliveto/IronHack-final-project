package com.bankingsystem.ironhackproject.service.accounts_service;

import com.bankingsystem.ironhackproject.model.accounts.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.Checking;

public interface CheckingService {
    Checking findCheckingByAccountId(Integer accountId);
    Checking updateCheckingBalance(Integer accountId, AccountBalanceUpdateDto balance);
}
