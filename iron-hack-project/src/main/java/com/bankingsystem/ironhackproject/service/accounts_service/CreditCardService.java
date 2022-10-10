package com.bankingsystem.ironhackproject.service.accounts_service;

import com.bankingsystem.ironhackproject.model.accounts.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.CreditCard;

public interface CreditCardService {
    CreditCard findCreditCardByAccountId(Integer accountId);

    CreditCard updateCreditCardBalance(Integer accountId, AccountBalanceUpdateDto balance);
}