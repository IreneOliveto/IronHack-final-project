package com.bankingsystem.ironhackproject.service.accounts_service;

import com.bankingsystem.ironhackproject.model.accounts.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.StudentChecking;

public interface StudentCheckingService {
    StudentChecking findStudentCheckingByAccountId(Integer accountId);

    StudentChecking updateStudentBalance(Integer accountId, AccountBalanceUpdateDto balance);
}

