package com.bankingsystem.ironhackproject.repository.accounts_repository;

import com.bankingsystem.ironhackproject.model.accounts.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
    Optional<CreditCard> findByAccountId(Integer accountId);
}

