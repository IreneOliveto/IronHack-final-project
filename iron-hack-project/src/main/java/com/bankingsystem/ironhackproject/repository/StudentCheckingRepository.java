package com.bankingsystem.ironhackproject.repository;

import com.bankingsystem.ironhackproject.model.accounts.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCheckingRepository extends JpaRepository<StudentChecking, Integer> {
}
