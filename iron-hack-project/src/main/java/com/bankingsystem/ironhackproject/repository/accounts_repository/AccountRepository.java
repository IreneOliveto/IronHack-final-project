package com.bankingsystem.ironhackproject.repository.accounts_repository;

import com.bankingsystem.ironhackproject.model.accounts.Account;
import com.bankingsystem.ironhackproject.model.accounts.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    /*@Query(value="select a.account_id, a.balance, " +
            "a.account_holder_user_id, a.penalty_fee, a.secondary_account_holder_user_id, a.creation_date, a.last_modified_date " +
            "from account a, account_holder ah " +
            "where a.account_holder_user_id = ah.user_id " +
            "and ah.name = :accountHolderName " +
            "and a.account_id = :accountId ",
            nativeQuery = true)*/
    Optional<Account> findByAccountIdAndAccountHolderName(@Valid Integer accountId, String accountHolder_name);
    Optional<Account> findByAccountId(Integer accountId);
    List<Account> findAllByAccountHolder_UserId(Integer userId);


}


