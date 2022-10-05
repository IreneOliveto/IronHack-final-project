package com.bankingsystem.ironhackproject.repository;

import com.bankingsystem.ironhackproject.model.accounts.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Integer> {
    Checking findByAccountId(Integer accountId);
    Optional<Checking> findById(Integer accountId);
}
