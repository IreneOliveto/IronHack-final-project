package com.bankingsystem.ironhackproject.model.accounts;

import com.bankingsystem.ironhackproject.ConfigSecurity.User;
import com.bankingsystem.ironhackproject.model.users.AccountHolder;
import com.bankingsystem.ironhackproject.model.utils.Money;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.Valid;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Valid
    protected Integer accountId;
    protected Money balance;
    @OneToOne
    @JsonIgnore
    protected AccountHolder accountHolder;
    protected BigDecimal penaltyFee;

    @Nullable
    @ManyToOne
    protected AccountHolder secondaryAccountHolder;

    @OneToOne
    @Valid
    protected User user;

    public Account(){}

    public Account(int accountId, Money balance, AccountHolder accountHolder, BigDecimal penaltyFee) {
        setAccountId(accountId);
        setBalance(balance);
        setPrimaryOwner(accountHolder);
        setPenaltyFee(penaltyFee);
    }

    public @Valid Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(@Valid Integer accountId) {
        this.accountId = accountId;
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

    public User getUser() {
        return user;
    }
    @JsonIgnore
    public void setUser(User user) {
        this.user = user;
    }
}
