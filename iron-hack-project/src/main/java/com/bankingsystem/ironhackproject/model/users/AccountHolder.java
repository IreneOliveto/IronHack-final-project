package com.bankingsystem.ironhackproject.model.users;

import com.bankingsystem.ironhackproject.ConfigSecurity.Role;
import com.bankingsystem.ironhackproject.ConfigSecurity.User;
import com.bankingsystem.ironhackproject.model.accounts.Account;
import com.bankingsystem.ironhackproject.model.utils.Address;
import com.bankingsystem.ironhackproject.model.utils.Money;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class AccountHolder extends User {
    protected String name;
    private Date dateOfBirth;
    @Nullable
    private String mailingAddress;
    @Embedded
    private Address primaryAddress;
    @OneToOne
    private Account account;

    // Constructor
    public AccountHolder(){}
    public AccountHolder(Integer userId, String username, String password, Set<Role> roles, String name, Date dateOfBirth, @Nullable String mailingAddress, Address primaryAddress, Account account) {
        super(userId, username, password, roles);
        setDateOfBirth(dateOfBirth);
        setName(name);
        setPrimaryAddress(primaryAddress);
        setDateOfBirth(dateOfBirth);
        setMailingAddress(mailingAddress);
        setAccount(account);
    }

    public AccountHolder(String name, Date dateOfBirth, @Nullable String mailingAddress, Address primaryAddress, Account account) {
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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
