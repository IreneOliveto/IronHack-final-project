package com.bankingsystem.ironhackproject.service.users_service;

import com.bankingsystem.ironhackproject.model.users.AccountHolder;
import com.bankingsystem.ironhackproject.repository.users_repository.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {
    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Override
    public boolean isAccountHolderOlderThanTwentyFour(Integer accountId) {
        LocalDate dateOfBirth = accountHolderRepository.findAccountHolderByAccountAccountId(accountId).getDateOfBirth();
        int yearsOld = Period.between(dateOfBirth, LocalDate.now()).getYears();
        return yearsOld >= 24;
    }

    @Override
    public AccountHolder saveNewAccountHolder(AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }

    @Override
    public AccountHolder getAccountHolderById(Integer accountHolderId) {
        return accountHolderRepository.findById(accountHolderId).get();
    }

}
