package com.bankingsystem.ironhackproject.service.accounts_service;

import com.bankingsystem.ironhackproject.model.accounts.dto.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.CreditCard;
import com.bankingsystem.ironhackproject.model.accounts.dto.DepositDto;

public interface CreditCardService {
    CreditCard findCreditCardByAccountId(Integer accountId);

    CreditCard saveNewCreditCard(CreditCard creditCard);

    CreditCard updateCreditCardBalance(Integer accountId, AccountBalanceUpdateDto balance);
}