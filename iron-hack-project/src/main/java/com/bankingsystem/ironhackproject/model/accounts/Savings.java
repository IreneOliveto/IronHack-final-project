package com.bankingsystem.ironhackproject.model.accounts;

import com.bankingsystem.ironhackproject.model.users.AccountHolder;
import com.bankingsystem.ironhackproject.model.utils.Money;
import com.bankingsystem.ironhackproject.model.utils.Status;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Savings extends Checking {
    private BigDecimal interestRate;

    @Nullable
    private LocalDate lastModifiedDate;

    public Savings() {
        super();
    }

    public Savings(int accountId, Money balance, AccountHolder accountHolder, BigDecimal penaltyFee, int secretKey, BigDecimal monthlyMaintenanceFee, BigDecimal minimumBalance, LocalDate creationDate, Status status, BigDecimal interestRate) {
        super(accountId, balance, accountHolder, penaltyFee, secretKey, minimumBalance, monthlyMaintenanceFee, creationDate, status);
        setInterestRate(interestRate);
        setMinimumBalance(minimumBalance);
    }

    @Override
    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = BigDecimal.ZERO;
    }

    @Override
    public void setMinimumBalance(BigDecimal minimumBalance) {
        if (minimumBalance == null) {
            this.minimumBalance = BigDecimal.valueOf(1000);
        } else {
            if (minimumBalance.compareTo(BigDecimal.valueOf(100)) < 0) {
                throw new IllegalArgumentException("The balance cannot be lower than 100.");
            } else {
                this.minimumBalance = minimumBalance;
            }
        }
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate == null) {
            this.interestRate = BigDecimal.valueOf(0.0025);
        } else {
            if (interestRate.compareTo(BigDecimal.valueOf(0.5)) > 0) {
                throw new IllegalArgumentException("The interest rate cannot be more than 0.5.");
            } else this.interestRate = interestRate;
        }
    }

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDate lastUpdateDate) {
        this.lastModifiedDate = lastUpdateDate;
    }

    @Override
    public Money getBalance() {
        LocalDate updateDate = getLastModifiedDate();
        LocalDate today = LocalDate.now();

        Period periodSinceUpdate = Period.between(updateDate != null ? updateDate : today, today);
        Period periodSinceCreation = Period.between(getCreationDate(), today);

        if(updateDate != null && periodSinceUpdate.getYears() > 1) {
            for (int i = 1; i <= periodSinceUpdate.getYears(); i++) {
                BigDecimal annualInterestRate = balance.getAmount().multiply(getInterestRate());
                BigDecimal newBalance = balance.getAmount().add(annualInterestRate);

                balance = new Money(newBalance);
                this.setLastModifiedDate(LocalDate.now());
            }
        } else if (updateDate == null && periodSinceCreation.getYears() > 1) {
            for (int i = 1; i <= periodSinceCreation.getYears(); i++) {
                BigDecimal annualInterestRate = balance.getAmount().multiply(interestRate);
                BigDecimal newBalance = balance.getAmount().add(annualInterestRate);

                balance = new Money(newBalance);
                this.setLastModifiedDate(LocalDate.now());
            }
        }
        return balance;
    }
}


/*
balance * (intRate ^ numYears)
        if(updateDate != null && periodSinceUpdate.getYears() > 1) {
            BigDecimal resultingBalance = (
                    savingsBalance
                            .multiply(interestRate.add(BigDecimal.ONE).pow(periodSinceUpdate.getYears())));

            Money updatedBalanceIR = new Money(resultingBalance,
                    Currency.getInstance("EUR"));

            setBalance(updatedBalanceIR);
            this.setLastModifiedDate(LocalDate.now());

        } else if (updateDate == null && periodSinceCreation.getYears() > 1) {
            BigDecimal resultingBalance = (
                    savingsBalance
                            .multiply(interestRate.add(BigDecimal.ONE).pow(periodSinceCreation.getYears())));

            Money updatedBalanceIR = new Money(resultingBalance,
                    Currency.getInstance("EUR"));

            setBalance(updatedBalanceIR);
            this.setLastModifiedDate(LocalDate.now());
        }
*/



