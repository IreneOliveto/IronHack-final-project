package com.bankingsystem.ironhackproject.service.accounts_service;

import com.bankingsystem.ironhackproject.model.accounts.Account;
import com.bankingsystem.ironhackproject.model.utils.Money;
import com.bankingsystem.ironhackproject.repository.accounts_repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements  AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public boolean transferMoney(Integer senderAccountId, String receiverName, Integer receiverAccountId, Money amount) {
        Optional<Account> receiverAccount = accountRepository.findByAccountIdAndAccountHolderName(receiverAccountId, receiverName);
        Optional<Account> senderAccount = accountRepository.findByAccountId(senderAccountId);
        if(receiverAccount.isPresent() && senderAccount.isPresent()) {
            senderAccount.get().transferMoney(receiverAccount.get(), amount);
            accountRepository.save(senderAccount.get());
            accountRepository.save(receiverAccount.get());
        }

        return receiverAccount.isPresent() && senderAccount.isPresent();
    }

    @Override
    public List<Account> getAllAccountsByUserId(Integer userId) {
        return accountRepository.findAllByAccountHolder_UserId(userId);
    }

}
