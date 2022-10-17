package com.bankingsystem.ironhackproject.model.accounts;

import com.bankingsystem.ironhackproject.ConfigSecurity.User;
import com.bankingsystem.ironhackproject.model.users.AccountHolder;
import com.bankingsystem.ironhackproject.model.utils.Money;

import javax.persistence.Entity;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class CreditCard extends Account {

    @Min(100)
    @Max(100000)
    private BigDecimal creditLimit;


    @DecimalMin("0.1")
    @DecimalMax("0.2")
    private BigDecimal interestRate;

    //Constructors
    public CreditCard() {
        super();
    }

    public CreditCard(int accountId, Money balance, AccountHolder accountHolder, BigDecimal penaltyFee, BigDecimal creditLimit, BigDecimal interestRate, LocalDate creationDate) {
        super(accountId, balance, accountHolder);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
        setCreationDate(creationDate);
    }

    public CreditCard(Integer accountId, Money balance, AccountHolder accountHolder, BigDecimal penaltyFee, AccountHolder secondaryAccountHolder, User user, LocalDate creationDate, LocalDate lastModifiedDate, BigDecimal creditLimit, BigDecimal interestRate) {
        super(accountId, balance, accountHolder, secondaryAccountHolder, creationDate, lastModifiedDate);
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
        int cardActiveMonths = monthsThisYear + monthsPerYear;

        BigDecimal annualInterestRateDividedPerMonth = getInterestRate().divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_EVEN);
        BigDecimal interestRatePerMonth = balance.getAmount().multiply(annualInterestRateDividedPerMonth);

        for (int i = 1; i <= cardActiveMonths; i++) {
            BigDecimal newBalance = balance.getAmount().add(interestRatePerMonth);
            interestRatePerMonth = balance.getAmount().multiply(annualInterestRateDividedPerMonth);
            balance = new Money(newBalance);
            this.setLastModifiedDate(LocalDate.now());
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