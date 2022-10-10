package com.bankingsystem.ironhackproject.controller;

import com.bankingsystem.ironhackproject.model.accounts.Checking;
import com.bankingsystem.ironhackproject.model.users.AccountHolder;
import com.bankingsystem.ironhackproject.model.utils.Money;
import com.bankingsystem.ironhackproject.repository.accounts_repository.CheckingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.math.BigDecimal;
class CheckingControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    CheckingRepository checkingRepository;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Money testBalance = new Money(BigDecimal.valueOf(400));
        AccountHolder testAccountHolder = new AccountHolder("Test1");
        Checking checking1 = new Checking(1, testBalance, testAccountHolder);

        checkingRepository.save(checking1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCheckingBalanceByAccountId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/checking/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Checking checking = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Checking.class );

        assertEquals(checking.getBalance(), BigDecimal.valueOf(400));
    }

}