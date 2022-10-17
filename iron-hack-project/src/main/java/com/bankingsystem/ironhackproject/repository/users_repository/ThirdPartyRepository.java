package com.bankingsystem.ironhackproject.repository.users_repository;

import com.bankingsystem.ironhackproject.model.accounts.Account;
import com.bankingsystem.ironhackproject.model.users.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Integer> {
    ThirdParty findThirdPartiesByUserId(Integer userId);
}
