package com.api.personcustomer.controller;

import com.api.personcustomer.exception.ResourceNotFoundException;
import com.api.personcustomer.service.AccountService;
import com.api.personcustomer.service.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "*")
public class AccountController {
    private final AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountDto accountDto) throws ResourceNotFoundException {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
    @PatchMapping("/disable")
    public ResponseEntity<?> disableAccountByAccountNumber(String accountNumber) throws ResourceNotFoundException {
        return new ResponseEntity<>(accountService.disableAccountByAccountNumber(accountNumber),HttpStatus.OK);
    }
}
