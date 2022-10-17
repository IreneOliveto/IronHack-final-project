package com.bankingsystem.ironhackproject.model;

import com.bankingsystem.ironhackproject.model.accounts.Checking;
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
class CheckingTest {
    final static int ACCOUNT_ID = 1;
    final static Money THOUSAND_EUROS = new Money(BigDecimal.valueOf(1000), Currency.getInstance("EUR"));
    final static AccountHolder ACCOUNT_HOLDER = new AccountHolder();
    final static BigDecimal EXPECTED_BALANCE_AFTER_PENALTY_FEE = BigDecimal.valueOf(176);
    final static int SECRET_KEY = 5;
    final static BigDecimal INVALID_MONTHLY_MAINTENANCE_FEE = BigDecimal.valueOf(150);
    final static BigDecimal EXPECTED_MONTHLY_MAINTENANCE_FEE = BigDecimal.valueOf(12);
    final static BigDecimal EXPECTED_BALANCE_AFTER_MAINTENANCE_FEE = BigDecimal.valueOf(556);
    final static BigDecimal MINIMUM_BALANCE = BigDecimal.valueOf(250);
    final static Money BALANCE_BELOW_MINIMUM_BALANCE = new Money(BigDecimal.valueOf(200), Currency.getInstance("EUR"));
    final static LocalDate CREATION_DATE = LocalDate.of(2019, 9, 11);
    final static LocalDate TWO_MONTHS_AGO = LocalDate.now().minusMonths(2);
    final static Status STATUS = Status.ACTIVE;

    @Test
    void whenCheckingDropBelowMinimumBalance_shouldPayThePenaltyFee() {
        Checking checking = new Checking(
                ACCOUNT_ID,
                BALANCE_BELOW_MINIMUM_BALANCE,
                ACCOUNT_HOLDER,
                SECRET_KEY,
                EXPECTED_MONTHLY_MAINTENANCE_FEE,
                MINIMUM_BALANCE,
                TWO_MONTHS_AGO,
                STATUS
        );

        assertEquals(EXPECTED_BALANCE_AFTER_PENALTY_FEE.setScale(2, RoundingMode.HALF_EVEN),
                checking.getBalance().getAmount().setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    void whenCreatingCheckingAccountWithInvalidMaintenanceFee_shouldHaveValidMaintenanceFee() {
        Checking checking = new Checking(
                ACCOUNT_ID,
                THOUSAND_EUROS,
                ACCOUNT_HOLDER,
                SECRET_KEY,
                INVALID_MONTHLY_MAINTENANCE_FEE,
                MINIMUM_BALANCE,
                CREATION_DATE,
                STATUS
        );
        assertEquals(EXPECTED_MONTHLY_MAINTENANCE_FEE, checking.getMonthlyMaintenanceFee());
    }

    @Test
    void whenCheckingAccountIsAccessed_shouldSubtractValidMaintenanceFee() {
        Checking checking = new Checking(
                ACCOUNT_ID,
                THOUSAND_EUROS,
                ACCOUNT_HOLDER,
                SECRET_KEY,
                INVALID_MONTHLY_MAINTENANCE_FEE,
                MINIMUM_BALANCE,
                CREATION_DATE,
                STATUS
        );
        assertEquals(EXPECTED_BALANCE_AFTER_MAINTENANCE_FEE.setScale(2, RoundingMode.HALF_EVEN),
                checking.getBalance().getAmount().setScale(2, RoundingMode.HALF_EVEN));
    }
}