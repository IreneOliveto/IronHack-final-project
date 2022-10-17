package com.bankingsystem.ironhackproject.controller.users_controller;

import com.bankingsystem.ironhackproject.model.users.AccountHolder;
import com.bankingsystem.ironhackproject.model.users.Admin;
import com.bankingsystem.ironhackproject.service.users_service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Admin getAdminByUserId (@PathVariable(value="userId") Integer userId) {
        return adminService.findAdminByUserId(userId).get();
    }
}
