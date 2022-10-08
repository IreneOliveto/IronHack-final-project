package com.bankingsystem.ironhackproject.service;

import com.bankingsystem.ironhackproject.model.accounts.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.Savings;

public interface SavingsService {
    Savings findSavingsByAccountId(Integer accountId);

    Savings updateBalance(Integer accountId, AccountBalanceUpdateDto balance);
}
