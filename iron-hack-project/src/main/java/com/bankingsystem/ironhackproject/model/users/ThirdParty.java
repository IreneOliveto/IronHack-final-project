package com.bankingsystem.ironhackproject.model.users;

import com.bankingsystem.ironhackproject.ConfigSecurity.User;

import javax.persistence.Entity;


@Entity
public class ThirdParty extends User {
    private String name;
    private Integer hashedKey;

    // Constructor
    public ThirdParty() {}

    public ThirdParty(String name, Integer hashedKey) {
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

    public Integer getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(Integer hashedKey) {
        this.hashedKey = hashedKey;
    }
}
