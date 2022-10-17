package com.bankingsystem.ironhackproject.service.accounts_service;

import com.bankingsystem.ironhackproject.model.accounts.dto.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.Checking;
import com.bankingsystem.ironhackproject.model.accounts.StudentChecking;

public interface StudentCheckingService {
    StudentChecking findStudentCheckingByAccountId(Integer accountId);
    StudentChecking saveNewStudentChecking(StudentChecking studentChecking);
    StudentChecking updateStudentBalance(Integer accountId, AccountBalanceUpdateDto balance);
}

