package com.bankingsystem.ironhackproject.model;

import com.bankingsystem.ironhackproject.model.accounts.CreditCard;
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
    final static Money EXPECTED_BALANCE = new Money(BigDecimal.valueOf(757.78));
    final static AccountHolder ACCOUNT_HOLDER = new AccountHolder();
    final static BigDecimal PENALTY_FEE = BigDecimal.valueOf(20);
    final static LocalDate CREATION_DATE = LocalDate.of(2021, 10, 10);
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
                null,
                null,
                CREATION_DATE
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
                VALID_CREDIT_LIMIT,
                null,
                CREATION_DATE
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
                null,
                VALID_INTEREST_RATE,
                CREATION_DATE
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
                        INVALID_CREDIT_LIMIT,
                        VALID_INTEREST_RATE,
                        CREATION_DATE
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
                VALID_CREDIT_LIMIT,
                INVALID_INTEREST_RATE,
                CREATION_DATE
        ));
    }

    @Test
    void whenCreditCardBalanceIsAccessed_shouldAddAppropriateInterestRate() {
        CreditCard creditCard = new CreditCard(
                ACCOUNT_ID,
                SIX_HUNDRED_EUROS,
                ACCOUNT_HOLDER,
                PENALTY_FEE,
                VALID_CREDIT_LIMIT,
                DEFAULT_INTEREST_RATE,
                CREATION_DATE
        );
        assertEquals(EXPECTED_BALANCE.getAmount(), creditCard.getBalance().getAmount());
    }
}