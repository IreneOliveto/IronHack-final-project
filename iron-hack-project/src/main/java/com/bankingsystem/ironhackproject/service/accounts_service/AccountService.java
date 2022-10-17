package com.bankingsystem.ironhackproject.service.accounts_service;

import com.bankingsystem.ironhackproject.model.accounts.Account;
import com.bankingsystem.ironhackproject.model.utils.Money;

import java.util.List;

public interface AccountService {
    boolean transferMoney(Integer senderAccount, String receiverName, Integer receiverAccountId, Money amount);
    List<Account> getAllAccountsByUserId(Integer userId);
}
