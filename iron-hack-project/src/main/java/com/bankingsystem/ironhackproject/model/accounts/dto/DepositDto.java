package com.bankingsystem.ironhackproject.model.accounts.dto;

import com.bankingsystem.ironhackproject.model.utils.Money;

public class DepositDto {
    private Integer receiverAccountId;
    private Money amount;
    private Integer secretKey;

    public DepositDto(Integer receiverAccountId, Money amount, int secretKey) {
        this.receiverAccountId = receiverAccountId;
        this.amount = amount;
        this.secretKey = secretKey;
    }

    public Integer getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(Integer receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public int getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(int secretKey) {
        this.secretKey = secretKey;
    }
}
