package com.api.movementsaccount.service.mapper;

import com.api.movementsaccount.model.Account;
import com.api.movementsaccount.service.dto.AccountDto;
import org.mapstruct.Mapper;

@Mapper
public interface AccountMapper {
    AccountDto accountToAccountDto(Account account);
}
