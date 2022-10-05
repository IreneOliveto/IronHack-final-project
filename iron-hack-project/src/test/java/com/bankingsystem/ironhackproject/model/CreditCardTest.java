package com.bankingsystem.ironhackproject.model;

import com.bankingsystem.ironhackproject.model.accounts.CreditCard;
import com.bankingsystem.ironhackproject.model.accounts.Savings;
import com.bankingsystem.ironhackproject.model.users.AccountHolder;
import com.bankingsystem.ironhackproject.model.utils.Money;
import com.bankingsystem.ironhackproject.model.utils.Status;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreditCardTest {
    final static int ACCOUNT_ID = 1;
    final static Money SIX_HUNDRED_EUROS = new Money(BigDecimal.valueOf(600), Currency.getInstance("EUR"));
    final static BigDecimal EXPECTED_BALANCE = BigDecimal.valueOf(690);
    final static AccountHolder ACCOUNT_HOLDER = new AccountHolder();
    final static BigDecimal PENALTY_FEE = BigDecimal.valueOf(20);
    final static int SECRET_KEY = 5;
    final static BigDecimal MONTHLY_MAINTENANCE_FEE = BigDecimal.valueOf(100);
    final static BigDecimal MINIMUM_BALANCE = BigDecimal.valueOf(100);
    final static LocalDate CREATION_DATE = LocalDate.of(2022, 1, 01);
    final static Status STATUS = Status.ACTIVE;
    final static BigDecimal DEFAULT_CREDIT_LIMIT = BigDecimal.valueOf(100);
    final static BigDecimal VALID_CREDIT_LIMIT = BigDecimal.valueOf(100000);
    final static BigDecimal INVALID_CREDIT_LIMIT = BigDecimal.valueOf(200000);

    final static BigDecimal DEFAULT_INTEREST_RATE = BigDecimal.valueOf(0.2);
    final static BigDecimal VALID_INTEREST_RATE = BigDecimal.valueOf(0.15);
    final static BigDecimal INVALID_INTEREST_RATE = BigDecimal.valueOf(0.05);

    @Test
    void whenCreatingCreditCard_shouldHaveDefaultCreditLimitAndDefaultInterestRate() {
        CreditCard creditcard = new CreditCard(
                ACCOUNT_ID,
                SIX_HUNDRED_EUROS,
                ACCOUNT_HOLDER,
                PENALTY_FEE,
                SECRET_KEY,
                MONTHLY_MAINTENANCE_FEE,
                MINIMUM_BALANCE,
                CREATION_DATE,
                STATUS,
                null,
                null

        );

        assertEquals(DEFAULT_CREDIT_LIMIT, creditcard.getCreditLimit());
        assertEquals(DEFAULT_INTEREST_RATE, creditcard.getInterestRate());
    }

    @Test
    void whenCreatingCreditCard_shouldHaveCreditLimitValueAndDefaultInterestRate() {
        CreditCard creditcard = new CreditCard(
                ACCOUNT_ID,
                SIX_HUNDRED_EUROS,
                ACCOUNT_HOLDER,
                PENALTY_FEE,
                SECRET_KEY,
                MONTHLY_MAINTENANCE_FEE,
                MINIMUM_BALANCE,
                CREATION_DATE,
                STATUS,
                VALID_CREDIT_LIMIT,
                null
        );

        assertEquals(DEFAULT_INTEREST_RATE, creditcard.getInterestRate());
    }

    @Test
    void whenCreatingCreditCard_shouldHaveDefaultCreditLimitAndInterestRateValue() {
        CreditCard creditcard = new CreditCard(
                ACCOUNT_ID,
                SIX_HUNDRED_EUROS,
                ACCOUNT_HOLDER,
                PENALTY_FEE,
                SECRET_KEY,
                MONTHLY_MAINTENANCE_FEE,
                MINIMUM_BALANCE,
                CREATION_DATE,
                STATUS,
                null,
                VALID_INTEREST_RATE
        );

        assertEquals(DEFAULT_CREDIT_LIMIT, creditcard.getCreditLimit());
    }

    @Test
    void whenCreditLimitIsNotValid_shouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> new CreditCard(
                ACCOUNT_ID,
                SIX_HUNDRED_EUROS,
                ACCOUNT_HOLDER,
                PENALTY_FEE,
                SECRET_KEY,
                MONTHLY_MAINTENANCE_FEE,
                MINIMUM_BALANCE,
                CREATION_DATE,
                STATUS,
                INVALID_CREDIT_LIMIT,
                VALID_INTEREST_RATE
        ));
    }

    @Test
    void whenInterestRateIsNotValid_shouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> new CreditCard(
                ACCOUNT_ID,
                SIX_HUNDRED_EUROS,
                ACCOUNT_HOLDER,
                PENALTY_FEE,
                SECRET_KEY,
                MONTHLY_MAINTENANCE_FEE,
                MINIMUM_BALANCE,
                CREATION_DATE,
                STATUS,
                VALID_CREDIT_LIMIT,
                INVALID_INTEREST_RATE
        ));
    }

    @Test
    void whenSavingBalanceIsAccessed_shouldAddAppropriateInterestRate() {
        CreditCard creditCard = new CreditCard(
                ACCOUNT_ID,
                SIX_HUNDRED_EUROS,
                ACCOUNT_HOLDER,
                PENALTY_FEE,
                SECRET_KEY,
                MONTHLY_MAINTENANCE_FEE,
                MINIMUM_BALANCE,
                CREATION_DATE,
                STATUS,
                VALID_CREDIT_LIMIT,
                DEFAULT_INTEREST_RATE
        );
        assertEquals(EXPECTED_BALANCE.setScale(2, RoundingMode.HALF_EVEN),
                creditCard.getBalance().getAmount().setScale(2, RoundingMode.HALF_EVEN));
    }
}