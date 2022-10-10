package com.bankingsystem.ironhackproject.model.accounts;

import com.bankingsystem.ironhackproject.ConfigSecurity.User;
import com.bankingsystem.ironhackproject.model.users.AccountHolder;
import com.bankingsystem.ironhackproject.model.utils.Money;
import com.bankingsystem.ironhackproject.model.utils.Status;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Checking extends Account {
    protected int secretKey;
    protected BigDecimal minimumBalance;
    protected BigDecimal monthlyMaintenanceFee;
    @Enumerated(EnumType.STRING)
    protected Status status;

    // Constructors
    public Checking() {
        super();
    }

    public Checking(int accountId, Money balance, AccountHolder accountHolder) {
        super();
    }

    public Checking(int accountId, Money balance, AccountHolder accountHolder, BigDecimal penaltyFee, int secretKey, BigDecimal minimumBalance, BigDecimal monthlyMaintenanceFee, LocalDate creationDate, Status status) {
        super(accountId, balance, accountHolder, penaltyFee);
        setSecretKey(secretKey);
        setMinimumBalance(minimumBalance);
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
        setCreationDate(creationDate);
        setStatus(status);
    }

    public Checking(Integer accountId, Money balance, AccountHolder accountHolder, BigDecimal penaltyFee, AccountHolder secondaryAccountHolder, User user, LocalDate creationDate, LocalDate lastModifiedDate, int secretKey, BigDecimal minimumBalance, BigDecimal monthlyMaintenanceFee, Status status) {
        super(accountId, balance, accountHolder, penaltyFee, secondaryAccountHolder, user, creationDate, lastModifiedDate);
        setSecretKey(secretKey);
        setMinimumBalance(minimumBalance);
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
        setCreationDate(creationDate);
        setStatus(status);
    }

    // Method
    private boolean applyMaintenanceFeeAndPenaltyFee() {
        Period periodSinceUpdateOrCreation = Period.between(getLastModifiedDate() == null ?  getCreationDate() : getLastModifiedDate(), LocalDate.now());
        int checkingActiveMonths = periodSinceUpdateOrCreation.getMonths() + periodSinceUpdateOrCreation.getYears() * 12;

        if (balance.getAmount().compareTo(minimumBalance) > 0) {
            if (checkingActiveMonths > 1) {
                BigDecimal totalMaintenanceFee = (BigDecimal.valueOf(checkingActiveMonths)
                        .multiply(getMonthlyMaintenanceFee()));
                BigDecimal newBalance = balance.getAmount().subtract(totalMaintenanceFee);

                balance = new Money(newBalance);
                return true;
            }
            return true;

        } else if (balance.getAmount().compareTo(minimumBalance) < 0) {
            BigDecimal balancePenaltyFeePaid = balance.getAmount().subtract(getPenaltyFee());
            balance = new Money(balancePenaltyFeePaid);
            return true;
        }
        return false;
    }

    // Getters & Setters
    public int getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(int secretKey) {
        this.secretKey = secretKey;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
            this.minimumBalance = BigDecimal.valueOf(250);
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = BigDecimal.valueOf(12);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public Money getBalance() {
        applyMaintenanceFeeAndPenaltyFee();
        return balance;
    }


}


