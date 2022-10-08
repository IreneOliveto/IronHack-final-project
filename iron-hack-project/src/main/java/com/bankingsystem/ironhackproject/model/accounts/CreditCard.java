package com.bankingsystem.ironhackproject.model.accounts;

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
    protected LocalDate creationDate;
    @Nullable
    private LocalDate lastModifiedDate;

    public CreditCard() {
        super();
    }

    public CreditCard(int accountId, Money balance, AccountHolder accountHolder, BigDecimal penaltyFee, BigDecimal creditLimit, BigDecimal interestRate, LocalDate creationDate) {
        super(accountId, balance, accountHolder, penaltyFee);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
        setCreationDate(creationDate);
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

    public void setLastModifiedDate(@Nullable LocalDate updateDate) {
        this.lastModifiedDate = updateDate;
    }

    @Override
    public Money getBalance() {
        LocalDate updateDate = getLastModifiedDate();

        Period periodSinceUpdate = Period.between(getLastModifiedDate() != null ? getLastModifiedDate() : LocalDate.now(), LocalDate.now());
        Period periodSinceCreation = Period.between(getCreationDate(), LocalDate.now());

        int activeMonthsSinceUpdate = (int) periodSinceUpdate.toTotalMonths();
        int activeMonthsSinceCreation = (int) periodSinceCreation.toTotalMonths();

        BigDecimal annualInterestRateDividedPerMonth = getInterestRate().divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_EVEN);
        BigDecimal interestRatePerMonth = balance.getAmount().multiply(annualInterestRateDividedPerMonth);

        if(updateDate != null && periodSinceUpdate.getMonths() > 1)  {

            for (int i = 1; i <= activeMonthsSinceUpdate; i++) {
                BigDecimal newBalance = balance.getAmount().add(interestRatePerMonth);
                balance = new Money(newBalance);
                this.setLastModifiedDate(LocalDate.now());
                i++;
            }
        } else if (updateDate == null && periodSinceCreation.getMonths() > 1){
            for (int i = 1; i <= activeMonthsSinceCreation; i++) {
                BigDecimal newBalance = balance.getAmount().add(interestRatePerMonth);
                balance = new Money(newBalance);
                this.setLastModifiedDate(LocalDate.now());
                i++;
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
