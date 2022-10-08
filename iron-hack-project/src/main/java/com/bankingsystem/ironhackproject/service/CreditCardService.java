package com.bankingsystem.ironhackproject.service;

import com.bankingsystem.ironhackproject.model.accounts.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.CreditCard;

public interface CreditCardService {
    CreditCard findCreditCardByAccountId(Integer accountId);

    CreditCard updateBalance(Integer accountId, AccountBalanceUpdateDto balance);
}