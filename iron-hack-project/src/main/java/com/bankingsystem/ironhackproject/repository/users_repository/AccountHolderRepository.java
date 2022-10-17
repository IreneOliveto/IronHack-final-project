package com.bankingsystem.ironhackproject.repository.users_repository;

import com.bankingsystem.ironhackproject.model.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Integer> {
    AccountHolder findAccountHolderByAccountAccountId(Integer accountId);
}
