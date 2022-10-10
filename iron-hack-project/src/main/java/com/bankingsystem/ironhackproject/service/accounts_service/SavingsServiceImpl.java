package com.bankingsystem.ironhackproject.service.accounts_service;

import com.bankingsystem.ironhackproject.model.accounts.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.Savings;
import com.bankingsystem.ironhackproject.repository.accounts_repository.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingsServiceImpl implements SavingsService {

    @Autowired
    SavingsRepository savingsRepository;

    @Override
    public Savings findSavingsByAccountId(Integer accountId) {
        return savingsRepository.findByAccountId(accountId).orElse(null);
    }

    @Override
    public Savings updateSavingsBalance(Integer accountId, AccountBalanceUpdateDto savingsBalance) {
        Savings storedBalance = findSavingsByAccountId(accountId);
        storedBalance.setBalance(savingsBalance.getBalance());
        return savingsRepository.save(storedBalance);
    }
}

