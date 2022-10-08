package com.bankingsystem.ironhackproject.model.users;

import com.bankingsystem.ironhackproject.ConfigSecurity.User;

import javax.persistence.Entity;

@Entity
public class Admin extends User {
    private String name;
}
