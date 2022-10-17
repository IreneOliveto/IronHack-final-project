package com.bankingsystem.ironhackproject.service.users_service;

import com.bankingsystem.ironhackproject.model.users.AccountHolder;

public interface AccountHolderService {
    boolean isAccountHolderOlderThanTwentyFour(Integer accountId);

    AccountHolder saveNewAccountHolder(AccountHolder accountHolder);

    AccountHolder getAccountHolderById(Integer accountHolderId);


}
