package com.bankingsystem.ironhackproject.service.accounts_service;

import com.bankingsystem.ironhackproject.model.accounts.dto.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.Savings;

public interface SavingsService {
    Savings findSavingsByAccountId(Integer accountId);
    Savings updateSavingsBalance(Integer accountId, AccountBalanceUpdateDto balance);
}
