package com.bankingsystem.ironhackproject.model.accounts;

import com.bankingsystem.ironhackproject.model.users.AccountHolder;
import com.bankingsystem.ironhackproject.model.utils.Money;
import com.bankingsystem.ironhackproject.model.utils.Status;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.Currency;

@Entity
public class CreditCard extends Checking {

    private BigDecimal creditLimit;
    private BigDecimal interestRate;

    public CreditCard() {
        super();
    }

    public CreditCard(int accountId, Money balance, AccountHolder accountHolder, BigDecimal penaltyFee, int secretKey, BigDecimal minimumBalance, BigDecimal monthlyMaintenanceFee, LocalDate creationDate, Status status, BigDecimal creditLimit, BigDecimal interestRate) {
        super(accountId, balance, accountHolder, penaltyFee, secretKey, minimumBalance, monthlyMaintenanceFee, creationDate, status);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
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

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
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

    @Override
    public Money getBalance() {
        Period periodSinceUpdate = Period.between(getUpdateDate() != null ? getUpdateDate() : LocalDate.now(), LocalDate.now());
        Period periodSinceCreation = Period.between(getCreationDate(), LocalDate.now());

        int activeMonthsSinceUpdate = periodSinceUpdate.getMonths() + (periodSinceUpdate.getYears()*12);
        int activeMonthsSinceCreation = periodSinceCreation.getMonths() + (periodSinceCreation.getYears()*12);

        BigDecimal annualInterestRateDividedPerMonth = getInterestRate().divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_EVEN);
        BigDecimal interestRatePerMonth = balance.getAmount().multiply(annualInterestRateDividedPerMonth);

        if(getUpdateDate() != null && periodSinceUpdate.getMonths() > 1) {

            for (int i = 1; i <= activeMonthsSinceUpdate; i++) {

                BigDecimal newBalance = balance.getAmount().add(interestRatePerMonth);

                balance = new Money(newBalance);
                this.setUpdateDate(LocalDate.now());
            }
        } else if (getUpdateDate() == null && activeMonthsSinceCreation > 1) {

            for (int i = 1; i <= activeMonthsSinceCreation; i++) {

                BigDecimal newBalance = balance.getAmount().add(interestRatePerMonth);

                balance = new Money(newBalance);
                this.setUpdateDate(LocalDate.now());
            }
        }

        return balance;
    }
}

        /* BigDecimal monthlyInterestRate = (
                creditCardBalance
                        .multiply(interestRate).divide(BigDecimal.valueOf(12))
                        .multiply(BigDecimal.valueOf(creditCardActiveMonths)));
        Money updatedBalanceMonthlyInterestRate = new Money(balance.increaseAmount(monthlyInterestRate),
                Currency.getInstance("EUR"));

        if(creditCardActiveMonths > 1) {
            return updatedBalanceMonthlyInterestRate;
        } */
