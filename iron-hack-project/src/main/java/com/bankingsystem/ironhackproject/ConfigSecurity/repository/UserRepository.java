package com.bankingsystem.ironhackproject.ConfigSecurity.repository;

import com.bankingsystem.ironhackproject.ConfigSecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

}
