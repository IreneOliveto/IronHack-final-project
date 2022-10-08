package com.bankingsystem.ironhackproject.service;

import com.bankingsystem.ironhackproject.model.accounts.StudentChecking;

public interface StudentCheckingService {
    StudentChecking findStudentCheckingByAccountId(Integer accountId);
}

