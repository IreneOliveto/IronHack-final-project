package com.bankingsystem.ironhackproject.service.accounts_service;

import com.bankingsystem.ironhackproject.model.accounts.dto.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.Savings;
import com.bankingsystem.ironhackproject.repository.accounts_repository.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class SavingsServiceImpl implements SavingsService {

    @Autowired
    SavingsRepository savingsRepository;

    @Override
    public Savings findSavingsByAccountId(Integer accountId) {
        Savings savingsBalance = savingsRepository.findByAccountId(accountId).orElseThrow(EntityNotFoundException::new);
        savingsBalance.setBalance(savingsBalance.getBalance());
        return savingsRepository.save(savingsBalance);
    }

    @Override
    public Savings updateSavingsBalance(Integer accountId, AccountBalanceUpdateDto savingsBalance) {
        Savings storedBalance = findSavingsByAccountId(accountId);
        storedBalance.setBalance(savingsBalance.getBalance());
        return savingsRepository.save(storedBalance);
    }
}

