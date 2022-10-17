package com.bankingsystem.ironhackproject.service.users_service;

import com.bankingsystem.ironhackproject.model.users.Admin;
import com.bankingsystem.ironhackproject.repository.users_repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements  AdminService{
    @Autowired
    AdminRepository adminRepository;

    @Override
    public Optional<Admin> findAdminByUserId(Integer userId) {
        return adminRepository.findAdminByUserId(userId);
    }
}
