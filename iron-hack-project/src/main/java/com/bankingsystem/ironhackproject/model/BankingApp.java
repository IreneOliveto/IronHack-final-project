package com.bankingsystem.ironhackproject.model;

import com.bankingsystem.ironhackproject.service.CheckingService;
import com.bankingsystem.ironhackproject.service.CreditCardService;
import com.bankingsystem.ironhackproject.service.SavingsService;
import com.bankingsystem.ironhackproject.service.StudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BankingApp {
    @Autowired
    private CheckingService checkingService;

    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private SavingsService savingsService;

    @Autowired
    private StudentCheckingService studentCheckingService;

    public CheckingService getCheckingService() {
        return checkingService;
    }

    public CreditCardService getCreditCardService() {
        return creditCardService;
    }

    public SavingsService getSavingsService() {
        return savingsService;
    }

    public StudentCheckingService getStudentCheckingService() {
        return studentCheckingService;
    }



}
