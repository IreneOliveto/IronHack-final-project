package com.bankingsystem.ironhackproject.model.accounts;

import com.bankingsystem.ironhackproject.model.utils.Money;

public class TransferDto {

    private String receiverName;
    private Integer receiverAccountId;
    private Money amount;

    public TransferDto(String receiverName, Integer receiverAccountId, Money amount) {
        this.receiverName = receiverName;
        this.receiverAccountId = receiverAccountId;
        this.amount = amount;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
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

}
