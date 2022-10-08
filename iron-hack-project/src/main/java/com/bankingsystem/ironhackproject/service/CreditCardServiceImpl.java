package com.bankingsystem.ironhackproject.service;

import com.bankingsystem.ironhackproject.model.accounts.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.CreditCard;
import com.bankingsystem.ironhackproject.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardServiceImpl implements CreditCardService{
    @Autowired
    CreditCardRepository creditCardRepository;

    @Override
    public CreditCard findCreditCardByAccountId(Integer accountId) {
        return creditCardRepository.findByAccountId(accountId).orElse(null);
    }

    @Override
    public CreditCard updateBalance(Integer accountId, AccountBalanceUpdateDto balance) {
        CreditCard storedBalance = findCreditCardByAccountId(accountId);
        storedBalance.setBalance(balance.getBalance());
        return creditCardRepository.save(storedBalance);
    }
}
