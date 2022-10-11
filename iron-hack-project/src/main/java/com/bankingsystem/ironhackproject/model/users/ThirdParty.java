package com.bankingsystem.ironhackproject.model.users;

import com.bankingsystem.ironhackproject.ConfigSecurity.User;

import javax.persistence.Entity;


@Entity
public class ThirdParty extends User {
    private String name;
    private int hashedKey;

    // Constructor
    public ThirdParty() {}

    public ThirdParty(String name, int hashedKey) {
        this.name = name;
        this.hashedKey = hashedKey;
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(int hashedKey) {
        this.hashedKey = hashedKey;
    }
}
