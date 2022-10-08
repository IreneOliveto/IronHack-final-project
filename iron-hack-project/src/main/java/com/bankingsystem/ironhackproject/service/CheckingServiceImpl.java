package com.bankingsystem.ironhackproject.service;

import com.bankingsystem.ironhackproject.model.accounts.Checking;
import com.bankingsystem.ironhackproject.repository.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckingServiceImpl implements CheckingService{

    @Autowired
    CheckingRepository checkingRepository;

    @Override
    public Checking findCheckingByAccountId(Integer accountId) {
        return checkingRepository.findByAccountId(accountId).orElse(null);
    }

}
