package com.api.personcustomer.service;

import com.api.personcustomer.exception.ResourceNotFoundException;
import com.api.personcustomer.service.dto.AccountDto;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto) throws ResourceNotFoundException;
    AccountDto disableAccountByAccountNumber(String accountNumber) throws ResourceNotFoundException;
}
