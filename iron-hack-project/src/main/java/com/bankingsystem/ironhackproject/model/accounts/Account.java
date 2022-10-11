package com.bankingsystem.ironhackproject.model.accounts;

import com.bankingsystem.ironhackproject.ConfigSecurity.User;
import com.bankingsystem.ironhackproject.model.users.AccountHolder;
import com.bankingsystem.ironhackproject.model.utils.Money;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Valid
    protected Integer accountId;
    protected Money balance;

    @JsonIgnore
    @OneToOne
    protected AccountHolder accountHolder;
    protected BigDecimal penaltyFee;
    @Nullable
    @ManyToOne
    protected AccountHolder secondaryAccountHolder;

    @Valid
    @PastOrPresent
    protected LocalDate creationDate;
    @Nullable
    private LocalDate lastModifiedDate;

    public Account(){}

    public Account(int accountId, Money balance, AccountHolder accountHolder, BigDecimal penaltyFee) {
        setAccountId(accountId);
        setBalance(balance);
        setPrimaryOwner(accountHolder);
        setPenaltyFee(penaltyFee);
    }

    public Account(Integer accountId, Money balance, AccountHolder accountHolder, BigDecimal penaltyFee, @Nullable AccountHolder secondaryAccountHolder, User user, LocalDate creationDate, @Nullable LocalDate lastModifiedDate) {
        setAccountId(accountId);
        setBalance(balance);
        setAccountHolder(accountHolder);
        setPenaltyFee(penaltyFee);
        setSecondaryAccountHolder(secondaryAccountHolder);
        setCreationDate(creationDate);
        setLastModifiedDate(lastModifiedDate);
    }

    public void transferMoney(Account accountReceiver, Money amountToSend) {
        if (balance.getAmount().compareTo(amountToSend.getAmount()) < 0) {
            throw new IllegalArgumentException("The amount to send cannot be greater than the account's current balance.");
        } else if (Objects.equals(accountId, accountReceiver.getAccountId())) {
            throw new IllegalArgumentException("The amount cannot be sent to the same account.");
        }
        setBalance(new Money(balance.getAmount().subtract(amountToSend.getAmount())));
        accountReceiver.setBalance(new Money(balance.getAmount().add(amountToSend.getAmount())));
    }

    public @Valid Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(@Valid Integer accountId) {
        this.accountId = accountId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Nullable
    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(@Nullable LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    @JsonIgnore
    public AccountHolder getPrimaryOwner() {
        return accountHolder;
    }

    public void setPrimaryOwner(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = BigDecimal.valueOf(40);
    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }

    @Nullable
    public AccountHolder getSecondaryAccountHolder() {
        return secondaryAccountHolder;
    }

    public void setSecondaryAccountHolder(@Nullable AccountHolder secondaryAccountHolder) {
        this.secondaryAccountHolder = secondaryAccountHolder;
    }
}
