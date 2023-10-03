package com.api.movementsaccount.controller;

import com.api.movementsaccount.exception.AtAlreadyExistsException;
import com.api.movementsaccount.exception.ResourceNotFoundException;
import com.api.movementsaccount.message.MessageSuccess;
import com.api.movementsaccount.service.AccountService;
import com.api.movementsaccount.service.dto.AccountDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {
    @Mock
    private AccountService accountService;
    @InjectMocks
    private AccountController accountController;
    @Test
    void getActiveAccountsByIdCli() throws ResourceNotFoundException {
        Mockito.when(accountService.getActiveAccountsByIdCli(Mockito.anyString())).thenReturn(new ArrayList<>());
        ResponseEntity<?> responseEntity = accountController.getActiveAccountsByIdCli("ID");
        Assert.isTrue(responseEntity.getStatusCode() == HttpStatus.OK, MessageSuccess.SUCCESS);
    }
    @Test
    void saveAccount() throws AtAlreadyExistsException {
        Mockito.when(accountService.createAccount(Mockito.any(AccountDto.class))).thenReturn(new AccountDto());
        ResponseEntity<?> responseEntity = accountController.saveAccount(new AccountDto());
        Assert.isTrue(responseEntity.getStatusCode() == HttpStatus.CREATED, MessageSuccess.SUCCESS);
    }
    @Test
    void disableAccountByAccountNumber() throws ResourceNotFoundException {
        Mockito.when(accountService.disableAccountByAccountNumber(Mockito.anyString())).thenReturn(new AccountDto());
        ResponseEntity<?> responseEntity = accountController.disableAccountByAccountNumber("ID");
        Assert.isTrue(responseEntity.getStatusCode() == HttpStatus.OK, MessageSuccess.SUCCESS);
    }
}
