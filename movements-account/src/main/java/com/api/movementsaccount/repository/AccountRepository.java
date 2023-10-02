package com.api.movementsaccount.repository;

import com.api.movementsaccount.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,String> {
    @Query("Select a from Account a where a.accountNumber = ?1")
    Optional<Account> getAccountByAccountNumber(String accountNumber);
}
