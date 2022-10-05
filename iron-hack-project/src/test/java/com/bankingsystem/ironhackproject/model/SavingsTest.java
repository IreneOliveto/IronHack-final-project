package com.bankingsystem.ironhackproject.model;

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
class SavingsTest {

    final static int ACCOUNT_ID = 1;
    final static Money SIX_HUNDRED_EUROS = new Money(BigDecimal.valueOf(600), Currency.getInstance("EUR"));
    final static AccountHolder ACCOUNT_HOLDER = new AccountHolder();
    final static BigDecimal PENALTY_FEE = BigDecimal.valueOf(20);
    final static int SECRET_KEY = 5;
    final static BigDecimal MONTHLY_MAINTENANCE_FEE = BigDecimal.valueOf(10);
    final static BigDecimal MINIMUM_BALANCE = BigDecimal.valueOf(150);

    final static BigDecimal INVALID_MINIMUM_BALANCE = BigDecimal.valueOf(10);
    final static LocalDate CREATION_DATE = LocalDate.of(2020, 9, 11);
    final static Status STATUS = Status.ACTIVE;
    final static BigDecimal INTEREST_RATE = BigDecimal.valueOf(0.01);
    final static BigDecimal EXPECTED_BALANCE = BigDecimal.valueOf(612.06);
    final static BigDecimal INVALID_INTEREST_RATE = BigDecimal.valueOf(0.7);

    final static BigDecimal DEFAULT_INTEREST_RATE = BigDecimal.valueOf(0.0025);

    final static BigDecimal DEFAULT_MINIMUM_BALANCE = BigDecimal.valueOf(1000);


    @Test
    void Savings_shouldNotHaveMaintenanceFee() {
        Savings savings = new Savings(
                ACCOUNT_ID,
                SIX_HUNDRED_EUROS,
                ACCOUNT_HOLDER,
                PENALTY_FEE,
                SECRET_KEY,
                MONTHLY_MAINTENANCE_FEE,
                null,
                CREATION_DATE,
                STATUS,
                null
        );
        assertEquals(BigDecimal.ZERO, savings.getMonthlyMaintenanceFee());
    }

    @Test
    void whenCreatingDefaultSaving_shouldHaveDefaultMinimumBalanceAndInterestRate() {
        Savings savings = new Savings(
                ACCOUNT_ID,
                SIX_HUNDRED_EUROS,
                ACCOUNT_HOLDER,
                PENALTY_FEE,
                SECRET_KEY,
                MONTHLY_MAINTENANCE_FEE,
                null,
                CREATION_DATE,
                STATUS,
                null
        );
        assertEquals(DEFAULT_MINIMUM_BALANCE, savings.getMinimumBalance());
        assertEquals(DEFAULT_INTEREST_RATE, savings.getInterestRate());
    }

    @Test
    void whenCreatingSavingWithDefaultMinBalance_shouldHaveDefaultMinBalanceAndInterestRateValue() {
        Savings savings = new Savings(
                ACCOUNT_ID,
                SIX_HUNDRED_EUROS,
                ACCOUNT_HOLDER,
                PENALTY_FEE,
                SECRET_KEY,
                MONTHLY_MAINTENANCE_FEE,
                null,
                CREATION_DATE,
                STATUS,
                INTEREST_RATE
        );
        assertEquals(DEFAULT_MINIMUM_BALANCE, savings.getMinimumBalance());
        assertEquals(INTEREST_RATE, savings.getInterestRate());
    }

    @Test
    void whenCreatingSavingWithDefaultInterestRate_shouldHaveDefaultInterestRateAndMinBalanceValue() {
        Savings savings = new Savings(
                ACCOUNT_ID,
                SIX_HUNDRED_EUROS,
                ACCOUNT_HOLDER,
                PENALTY_FEE,
                SECRET_KEY,
                MONTHLY_MAINTENANCE_FEE,
                MINIMUM_BALANCE,
                CREATION_DATE,
                STATUS,
                null
        );
        assertEquals(MINIMUM_BALANCE, savings.getMinimumBalance());
        assertEquals(DEFAULT_INTEREST_RATE, savings.getInterestRate());
    }

    @Test
    void whenCreatingSaving_shouldHaveInterestRateValueAndMinBalanceValue() {
        Savings savings = new Savings(
                ACCOUNT_ID,
                SIX_HUNDRED_EUROS,
                ACCOUNT_HOLDER,
                PENALTY_FEE,
                SECRET_KEY,
                MONTHLY_MAINTENANCE_FEE,
                MINIMUM_BALANCE,
                CREATION_DATE,
                STATUS,
                INTEREST_RATE
        );
        assertEquals(MINIMUM_BALANCE, savings.getMinimumBalance());
        assertEquals(INTEREST_RATE, savings.getInterestRate());
    }

    @Test
    void whenCreatingSavingWithInvalidInterestRate_shouldThrowException() {

        assertThrows(IllegalArgumentException.class,
                () -> new Savings(
                    ACCOUNT_ID,
                    SIX_HUNDRED_EUROS,
                    ACCOUNT_HOLDER,
                    PENALTY_FEE,
                    SECRET_KEY,
                    MONTHLY_MAINTENANCE_FEE,
                    MINIMUM_BALANCE,
                    CREATION_DATE,
                    STATUS,
                    INVALID_INTEREST_RATE
        ));
    }

    @Test
    void whenCreatingSavingWithInvalidMinimumBalance_shouldThrowException() {

        assertThrows(IllegalArgumentException.class,
                () -> new Savings(
                        ACCOUNT_ID,
                        SIX_HUNDRED_EUROS,
                        ACCOUNT_HOLDER,
                        PENALTY_FEE,
                        SECRET_KEY,
                        MONTHLY_MAINTENANCE_FEE,
                        INVALID_MINIMUM_BALANCE,
                        CREATION_DATE,
                        STATUS,
                        INTEREST_RATE
                ));
    }

    @Test
    void whenSavingBalanceIsAccessed_shouldAddAppropriateInterestRate() {
        Savings savings = new Savings(
                ACCOUNT_ID,
                SIX_HUNDRED_EUROS,
                ACCOUNT_HOLDER,
                PENALTY_FEE,
                SECRET_KEY,
                MONTHLY_MAINTENANCE_FEE,
                MINIMUM_BALANCE,
                CREATION_DATE,
                STATUS,
                INTEREST_RATE
        );
        Money balance = savings.getBalance();
        assertEquals(EXPECTED_BALANCE.setScale(2, RoundingMode.HALF_EVEN), balance.getAmount().setScale(2, RoundingMode.HALF_EVEN));
    }


}