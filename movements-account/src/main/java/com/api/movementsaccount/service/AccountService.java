package com.api.movementsaccount.service;

import com.api.movementsaccount.exception.AtAlreadyExistsException;
import com.api.movementsaccount.exception.MovementException;
import com.api.movementsaccount.exception.ResourceNotFoundException;
import com.api.movementsaccount.service.dto.AccountDto;
import com.api.movementsaccount.service.dto.MovementDto;

public interface AccountService {
    AccountDto getAccountByAccountNumber(String accountNumber) throws ResourceNotFoundException;
    AccountDto updateInitialBalance(MovementDto movementDto) throws ResourceNotFoundException, MovementException;
    AccountDto createAccount(AccountDto accountDto) throws AtAlreadyExistsException;
    AccountDto disableAccountByAccountNumber(String accountNumber) throws ResourceNotFoundException;
}
