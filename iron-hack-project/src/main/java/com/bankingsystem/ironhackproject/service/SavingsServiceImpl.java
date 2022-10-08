package com.bankingsystem.ironhackproject.service;

import com.bankingsystem.ironhackproject.model.accounts.Savings;
import com.bankingsystem.ironhackproject.repository.SavingsRepository;
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
}

