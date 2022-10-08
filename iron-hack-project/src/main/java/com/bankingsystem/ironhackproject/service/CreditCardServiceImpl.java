package com.bankingsystem.ironhackproject.service;

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
}
