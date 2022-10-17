package com.bankingsystem.ironhackproject.model.accounts.dto;

import com.bankingsystem.ironhackproject.model.utils.Money;

public class TransferDto {

    private Integer senderAccountId;
    private String receiverName;
    private Integer receiverAccountId;
    private Money amount;

    public TransferDto(Integer senderAccountId, String receiverName, Integer receiverAccountId, Money amount) {
        this.senderAccountId = senderAccountId;
        this.receiverName = receiverName;
        this.receiverAccountId = receiverAccountId;
        this.amount = amount;
    }

    public Integer getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(Integer senderAccountId) {
        this.senderAccountId = senderAccountId;
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
