package com.bankingsystem.ironhackproject.model.accounts;

import com.bankingsystem.ironhackproject.ConfigSecurity.User;
import com.bankingsystem.ironhackproject.model.users.AccountHolder;
import com.bankingsystem.ironhackproject.model.utils.Money;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class CreditCard extends Account {

    private BigDecimal creditLimit;
    private BigDecimal interestRate;

    //Constructors
    public CreditCard() {
        super();
    }

    public CreditCard(int accountId, Money balance, AccountHolder accountHolder, BigDecimal penaltyFee, BigDecimal creditLimit, BigDecimal interestRate, LocalDate creationDate) {
        super(accountId, balance, accountHolder, penaltyFee);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
        setCreationDate(creationDate);
    }

    public CreditCard(Integer accountId, Money balance, AccountHolder accountHolder, BigDecimal penaltyFee, AccountHolder secondaryAccountHolder, User user, LocalDate creationDate, LocalDate lastModifiedDate, BigDecimal creditLimit, BigDecimal interestRate) {
        super(accountId, balance, accountHolder, penaltyFee, secondaryAccountHolder, user, creationDate, lastModifiedDate);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    //Methods
    private void applyCreditLimit(BigDecimal creditLimit) {
        if(creditLimit == null) {
            this.creditLimit = BigDecimal.valueOf(100);
        } else {
            if(creditLimit.compareTo(BigDecimal.valueOf(100000)) > 0 || creditLimit.compareTo(BigDecimal.valueOf(100)) < 0 ) {
                throw new IllegalArgumentException("The balance should be between 100 and 100.000€");
            } else {
                this.creditLimit = creditLimit;
            }
        }
    }

    private void applyInterestRate(BigDecimal interestRate) {
        if(interestRate == null) {
            this.interestRate = BigDecimal.valueOf(0.2);
        } else {
            if(interestRate.compareTo(BigDecimal.valueOf(0.2)) > 0 || interestRate.compareTo(BigDecimal.valueOf(0.1)) < 1) {
                throw new IllegalArgumentException("The interest rate should be between 0.1 and 0.2");
            } else {
                this.interestRate = interestRate;
            }
        }
    }

    private void applyInterestRate() {
        Period periodSinceUpdateOrCreation = Period.between(getLastModifiedDate() == null ?  getCreationDate() : getLastModifiedDate(), LocalDate.now());

        int monthsPerYear = periodSinceUpdateOrCreation.getYears() * 12;
        int monthsThisYear = periodSinceUpdateOrCreation.getMonths();
        int CardActiveMonths = monthsThisYear + monthsPerYear;

        BigDecimal annualInterestRateDividedPerMonth = getInterestRate().divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_EVEN);
        BigDecimal interestRatePerMonth = balance.getAmount().multiply(annualInterestRateDividedPerMonth);

        if(CardActiveMonths > 1) {
            for (int i = 1; i <= CardActiveMonths; i++) {
                BigDecimal newBalance = balance.getAmount().add(interestRatePerMonth);
                balance = new Money(newBalance);
                this.setLastModifiedDate(LocalDate.now());
                i++;
            }
        }
    }

    // Getters & Setters
    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        applyCreditLimit(creditLimit);
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        applyInterestRate(interestRate);
    }

    @Override
    public Money getBalance() {
        applyInterestRate();
        return balance;
    }
}