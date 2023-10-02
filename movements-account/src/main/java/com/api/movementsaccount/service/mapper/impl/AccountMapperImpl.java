package com.api.movementsaccount.service.mapper.impl;

import com.api.movementsaccount.model.Account;
import com.api.movementsaccount.service.dto.AccountDto;
import com.api.movementsaccount.service.mapper.AccountMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperImpl implements AccountMapper {
    @Override
    public AccountDto accountToAccountDto(Account account) {
        return new AccountDto(account.getIdCue(),
                account.getCustomer().getIdCli(),
                account.getAccountType().getIdTcu(),
                account.getAccountNumber(),
                account.getInitialBalance(),
                account.getState()
        );
    }
}
