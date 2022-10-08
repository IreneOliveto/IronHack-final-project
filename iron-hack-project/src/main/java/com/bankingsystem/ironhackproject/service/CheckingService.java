package com.bankingsystem.ironhackproject.service;

import com.bankingsystem.ironhackproject.model.accounts.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.Checking;

public interface CheckingService {
    Checking findCheckingByAccountId(Integer accountId);
    Checking updateBalance(Integer accountId, AccountBalanceUpdateDto balance);
}
