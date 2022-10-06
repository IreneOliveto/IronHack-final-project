package com.bankingsystem.ironhackproject.model.accounts;

import com.bankingsystem.ironhackproject.model.users.AccountHolder;
import com.bankingsystem.ironhackproject.model.utils.Money;
import com.bankingsystem.ironhackproject.model.utils.Status;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Currency;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Checking extends Account {
    protected int secretKey;
    protected BigDecimal minimumBalance;
    protected BigDecimal monthlyMaintenanceFee;
    protected LocalDate creationDate;
    @Nullable
    private LocalDate updateDate;

    @Enumerated(EnumType.STRING)
    protected Status status;

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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Nullable
    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(@Nullable LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public Money getBalance() {

        LocalDate creationDate = getCreationDate();
        LocalDate updateDate = getUpdateDate();
        LocalDate today = LocalDate.now();

        Period periodSinceUpdate = Period.between(updateDate != null ? updateDate : today, today);

        int monthsPerYear = periodSinceUpdate.getYears() * 12;
        int monthsThisYear = periodSinceUpdate.getMonths();
        int checkingActiveMonths = monthsThisYear + monthsPerYear;

        BigDecimal checkingAmount = balance.getAmount();
        BigDecimal checkingMaintenanceFee = getMonthlyMaintenanceFee();

        if (checkingAmount.compareTo(minimumBalance) > 0) {
            if (checkingActiveMonths > 1) {
                BigDecimal totalMaintenanceFee = (BigDecimal.valueOf(checkingActiveMonths)
                        .multiply(checkingMaintenanceFee));
                BigDecimal newBalance = checkingAmount.subtract(totalMaintenanceFee);

                balance = new Money(newBalance);
                return balance;
            }
            return balance;

        } else if (checkingAmount.compareTo(minimumBalance) < 0) {
            BigDecimal balancePenaltyFeePaid = checkingAmount.subtract(getPenaltyFee());
            balance = new Money(balancePenaltyFeePaid);
            return balance;
        }
        return balance;
    }
}


