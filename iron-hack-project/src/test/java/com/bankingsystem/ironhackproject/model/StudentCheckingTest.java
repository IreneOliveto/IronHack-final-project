package com.bankingsystem.ironhackproject.model;

import com.bankingsystem.ironhackproject.model.accounts.StudentChecking;
import com.bankingsystem.ironhackproject.model.users.AccountHolder;
import com.bankingsystem.ironhackproject.model.utils.Money;
import com.bankingsystem.ironhackproject.model.utils.Status;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class StudentCheckingTest {
    final static int ACCOUNT_ID = 1;
    final static Money SIX_HUNDRED_EUROS = new Money(BigDecimal.valueOf(600), Currency.getInstance("EUR"));
    final static AccountHolder ACCOUNT_HOLDER = new AccountHolder();
    final static BigDecimal PENALTY_FEE = BigDecimal.valueOf(20);
    final static int SECRET_KEY = 5;
    final static BigDecimal MONTHLY_MAINTENANCE_FEE = BigDecimal.valueOf(100);
    final static BigDecimal MINIMUM_BALANCE = BigDecimal.valueOf(100);
    final static LocalDate CREATION_DATE = LocalDate.of(2019, 9, 11);
    final static Status STATUS = Status.ACTIVE;

    @Test
    void StudentChecking_shouldNotHaveMaintenanceFeeAndMinimumBalance() {
        StudentChecking studentChecking = new StudentChecking(
                ACCOUNT_ID,
                SIX_HUNDRED_EUROS,
                ACCOUNT_HOLDER,
                PENALTY_FEE,
                SECRET_KEY,
                MONTHLY_MAINTENANCE_FEE,
                MINIMUM_BALANCE,
                CREATION_DATE,
                STATUS
        );
        assertNull(studentChecking.getMinimumBalance());
        assertEquals(BigDecimal.valueOf(0), studentChecking.getMonthlyMaintenanceFee());
    }

}