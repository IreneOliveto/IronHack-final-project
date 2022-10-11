package com.bankingsystem.ironhackproject.service.accounts_service;

import com.bankingsystem.ironhackproject.model.utils.Money;

public interface AccountService {
    boolean transferMoney(String receiverName, Integer receiverAccountId, Money amount);
}
