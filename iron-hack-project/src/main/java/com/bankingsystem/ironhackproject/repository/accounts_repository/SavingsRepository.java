package com.bankingsystem.ironhackproject.repository.accounts_repository;

import com.bankingsystem.ironhackproject.model.accounts.Savings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SavingsRepository extends JpaRepository<Savings, Integer> {
    Optional<Savings> findByAccountId(Integer accountId);
}

