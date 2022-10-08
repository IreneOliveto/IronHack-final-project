package com.bankingsystem.ironhackproject.service;

import com.bankingsystem.ironhackproject.model.accounts.CreditCard;

public interface CreditCardService {
    CreditCard findCreditCardByAccountId(Integer accountId);
}