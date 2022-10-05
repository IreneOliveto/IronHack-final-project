package com.bankingsystem.ironhackproject.repository;

import com.bankingsystem.ironhackproject.model.accounts.Savings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsRepository extends JpaRepository<Savings, Integer> {
}
