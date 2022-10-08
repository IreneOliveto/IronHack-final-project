package com.bankingsystem.ironhackproject.service;

import com.bankingsystem.ironhackproject.model.accounts.StudentChecking;
import com.bankingsystem.ironhackproject.repository.StudentCheckingRepository;
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
}
