package com.bankingsystem.ironhackproject.controller;

import com.bankingsystem.ironhackproject.model.accounts.StudentChecking;
import com.bankingsystem.ironhackproject.service.StudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentCheckingController {

    @Autowired
    StudentCheckingService studentCheckingService;

    @GetMapping("/student/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    StudentChecking getStudentChecking(@PathVariable(value="accountId") Integer accountId) {
        return studentCheckingService.findStudentCheckingByAccountId(accountId);
    }
}
