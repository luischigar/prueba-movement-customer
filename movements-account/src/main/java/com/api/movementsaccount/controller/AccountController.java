package com.api.movementsaccount.controller;

import com.api.movementsaccount.exception.AtAlreadyExistsException;
import com.api.movementsaccount.exception.ResourceNotFoundException;
import com.api.movementsaccount.service.AccountService;
import com.api.movementsaccount.service.dto.AccountDto;
import jakarta.validation.Valid;
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
    @GetMapping("/active-accounts")
    public ResponseEntity<?> getActiveAccountsByIdCli(String idCli) throws ResourceNotFoundException {
        return new ResponseEntity<>(accountService.getActiveAccountsByIdCli(idCli),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> saveAccount(@Valid @RequestBody AccountDto accountDto) throws AtAlreadyExistsException {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
    @PatchMapping("/disable")
    public ResponseEntity<?> disableAccountByAccountNumber(String accountNumber) throws ResourceNotFoundException {
        return new ResponseEntity<>(accountService.disableAccountByAccountNumber(accountNumber),HttpStatus.OK);
    }
}
