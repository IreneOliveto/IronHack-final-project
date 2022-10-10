package com.bankingsystem.ironhackproject.service.accounts_service;

import com.bankingsystem.ironhackproject.model.accounts.AccountBalanceUpdateDto;
import com.bankingsystem.ironhackproject.model.accounts.StudentChecking;
import com.bankingsystem.ironhackproject.repository.accounts_repository.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCheckingServiceImpl implements StudentCheckingService{

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    @Override
    public StudentChecking findStudentCheckingByAccountId(Integer accountId) {
        return studentCheckingRepository.findByAccountId(accountId).orElse(null);
    }

    @Override
    public StudentChecking updateStudentBalance(Integer accountId, AccountBalanceUpdateDto balance) {
        StudentChecking storedBalance = findStudentCheckingByAccountId(accountId);
        storedBalance.setBalance(balance.getBalance());
        return studentCheckingRepository.save(storedBalance);
    }
}
