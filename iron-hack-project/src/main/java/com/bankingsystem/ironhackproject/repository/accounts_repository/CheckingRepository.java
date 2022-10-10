package com.bankingsystem.ironhackproject.repository.accounts_repository;

import com.bankingsystem.ironhackproject.model.accounts.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Integer> {
    Optional<Checking> findByAccountId(Integer accountId);
}


