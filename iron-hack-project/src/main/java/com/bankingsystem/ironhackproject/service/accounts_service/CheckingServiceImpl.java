package com.bankingsystem.ironhackproject.service.accounts_service;

import com.bankingsystem.ironhackproject.model.accounts.dto.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.Checking;
import com.bankingsystem.ironhackproject.model.utils.Money;
import com.bankingsystem.ironhackproject.repository.accounts_repository.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

@Service
public class CheckingServiceImpl implements CheckingService{

    @Autowired
    CheckingRepository checkingRepository;

    @Override
    public Checking findCheckingByAccountId(Integer accountId) {
        return checkingRepository.findByAccountId(accountId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Checking saveNewChecking(Checking checking) {
        return checkingRepository.save(checking);
    }

    @Override
    public Checking updateCheckingBalance(Integer accountId, AccountBalanceUpdateDto balance) {
        Checking storedBalance = findCheckingByAccountId(accountId);
        storedBalance.setBalance(balance.getBalance());
        return checkingRepository.save(storedBalance);
    }

    @Override
    public Checking increaseCheckingBalance(Integer accountId, AccountBalanceUpdateDto balance) {
        Checking storedBalance = findCheckingByAccountId(accountId);
        storedBalance.setBalance(new Money(storedBalance.getBalance().increaseAmount(balance.getBalance())));
        return checkingRepository.save(storedBalance);
    }

    @Override
    public boolean withdrawalChecking(Integer accountId, Money amount, Integer secretKey) {
        Checking storedBalance = findCheckingByAccountId(accountId);
        storedBalance.setBalance(new Money(storedBalance.getBalance().increaseAmount(amount.getAmount())));
        checkingRepository.save(storedBalance);
        return true;
    }

}
