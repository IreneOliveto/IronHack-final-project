package com.bankingsystem.ironhackproject.ConfigSecurity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private Integer roleId;

    private String name;

    @ManyToOne
    private User user;

    public Integer getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
