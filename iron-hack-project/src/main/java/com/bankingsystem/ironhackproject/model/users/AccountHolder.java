package com.bankingsystem.ironhackproject.model.users;

import com.bankingsystem.ironhackproject.ConfigSecurity.Role;
import com.bankingsystem.ironhackproject.ConfigSecurity.User;
import com.bankingsystem.ironhackproject.model.accounts.Account;
import com.bankingsystem.ironhackproject.model.utils.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

@Entity
public class AccountHolder extends User {
    protected String name;
    private LocalDate dateOfBirth;
    @Nullable
    private String mailingAddress;
    @Embedded
    private Address primaryAddress;
    @JsonIgnore
    @OneToOne
    private Account account;

    // Constructor
    public AccountHolder(){}
    public AccountHolder(Integer userId, String username, String password, Set<Role> roles, String name, Account account) {
        super(userId, username, password, roles);
        setName(name);
        setAccount(account);
    }

    public AccountHolder(String name, LocalDate dateOfBirth, @Nullable String mailingAddress, Address primaryAddress, Account account) {
        setDateOfBirth(dateOfBirth);
        setName(name);
        setPrimaryAddress(primaryAddress);
        setDateOfBirth(dateOfBirth);
        setMailingAddress(mailingAddress);
        setAccount(account);
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Nullable
    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(@Nullable String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
