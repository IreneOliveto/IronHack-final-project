package com.bankingsystem.ironhackproject.service.users_service;

import com.bankingsystem.ironhackproject.model.users.Admin;

import java.util.Optional;

public interface AdminService {
    Optional<Admin> findAdminByUserId(Integer userId);
}
