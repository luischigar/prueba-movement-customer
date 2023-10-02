package com.api.movementsaccount.service;

import com.api.movementsaccount.exception.AtAlreadyExistsException;
import com.api.movementsaccount.exception.MovementException;
import com.api.movementsaccount.exception.ResourceNotFoundException;
import com.api.movementsaccount.service.dto.AccountDto;
import com.api.movementsaccount.service.dto.MovementDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> getActiveAccountsByIdCli(String idCli) throws ResourceNotFoundException;
    AccountDto updateInitialBalance(MovementDto movementDto) throws ResourceNotFoundException, MovementException;
    AccountDto createAccount(AccountDto accountDto) throws AtAlreadyExistsException;
    AccountDto disableAccountByAccountNumber(String accountNumber) throws ResourceNotFoundException;
}
