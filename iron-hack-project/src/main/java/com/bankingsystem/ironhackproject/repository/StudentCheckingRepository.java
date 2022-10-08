package com.bankingsystem.ironhackproject.repository;

import com.bankingsystem.ironhackproject.model.accounts.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentCheckingRepository extends JpaRepository<StudentChecking, Integer> {
    Optional<StudentChecking> findByAccountId(Integer accountId);
}

