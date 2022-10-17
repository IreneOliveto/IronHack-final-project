package com.bankingsystem.ironhackproject.controller;

import com.bankingsystem.ironhackproject.controller.accounts_controller.CheckingController;
import com.bankingsystem.ironhackproject.model.accounts.Checking;
import com.bankingsystem.ironhackproject.model.users.AccountHolder;
import com.bankingsystem.ironhackproject.model.utils.Money;
import com.bankingsystem.ironhackproject.model.utils.Status;
import com.bankingsystem.ironhackproject.repository.accounts_repository.CheckingRepository;
import com.bankingsystem.ironhackproject.service.accounts_service.CheckingService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
class CheckingControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    CheckingRepository checkingRepository;
    @Mock
    CheckingService checkingService;
    @InjectMocks
    CheckingController checkingController;

    @Test
    void getCheckingBalanceByAccountId() throws Exception {
        int ACCOUNT_ID_TEST = 13;
        Money BALANCE_TEST = new Money(BigDecimal.valueOf(8000));
        AccountHolder ACCOUNT_HOLDER_TEST = new AccountHolder();
        int SECRET_KEY_TEST = 123;
        BigDecimal MINIMUM_BALANCE_TEST = BigDecimal.valueOf(250);
        BigDecimal MAINTENANCE_FEE_TEST = BigDecimal.valueOf(12);
        LocalDate CREATION_DATE_TEST = LocalDate.of(2022, 5, 5);
        Status STATUS_TEST = Status.ACTIVE;

        Checking checkingTest = new Checking(ACCOUNT_ID_TEST, BALANCE_TEST, ACCOUNT_HOLDER_TEST, SECRET_KEY_TEST, MINIMUM_BALANCE_TEST, MAINTENANCE_FEE_TEST, CREATION_DATE_TEST, STATUS_TEST);


        when(checkingService.findCheckingByAccountId(ACCOUNT_ID_TEST)).thenReturn(checkingTest);
        checkingController.getChecking(ACCOUNT_ID_TEST);
        verify(checkingService).findCheckingByAccountId(13);
    }

}