package com.bankingsystem.ironhackproject.service;

import com.bankingsystem.ironhackproject.model.accounts.Checking;

public interface CheckingService {
    Checking findCheckingByAccountId(Integer accountId);
}
