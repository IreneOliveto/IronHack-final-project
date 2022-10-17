package com.bankingsystem.ironhackproject.repository.users_repository;

import com.bankingsystem.ironhackproject.model.users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findAdminByUserId(Integer userId);
}
