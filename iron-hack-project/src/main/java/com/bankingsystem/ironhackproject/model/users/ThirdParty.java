package com.bankingsystem.ironhackproject.model.users;

import com.bankingsystem.ironhackproject.ConfigSecurity.User;

import javax.persistence.Entity;

@Entity
public class ThirdParty extends User {
    private String name;
    private int hashedKey;
}
