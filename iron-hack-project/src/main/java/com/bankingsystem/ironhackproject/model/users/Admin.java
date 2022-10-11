package com.bankingsystem.ironhackproject.model.users;

import com.bankingsystem.ironhackproject.ConfigSecurity.Role;
import com.bankingsystem.ironhackproject.ConfigSecurity.User;

import javax.persistence.Entity;
import java.util.Set;

@Entity
public class Admin extends User {
    private String name;

    //Constructor
    public Admin() {}

    public Admin(Integer userId, String username, String password, Set<Role> roles, String name) {
        super(userId, username, password, roles);
        setName(name);
    }

    // Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
