package com.bankingsystem.ironhackproject.model.accounts;

import com.bankingsystem.ironhackproject.model.utils.Money;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountBalanceUpdateDto {
    private Money balance;
    public AccountBalanceUpdateDto(@JsonProperty("balance") Money balance) {
        this.balance = balance;
    }
    public Money getBalance() {
        return balance;
    }
    public void setBalance(Money balance) {
        this.balance = balance;
    }
}
