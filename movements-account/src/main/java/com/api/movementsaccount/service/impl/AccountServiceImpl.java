package com.api.movementsaccount.service.impl;

import com.api.movementsaccount.exception.AtAlreadyExistsException;
import com.api.movementsaccount.exception.MovementException;
import com.api.movementsaccount.exception.ResourceNotFoundException;
import com.api.movementsaccount.message.MessageMistakes;
import com.api.movementsaccount.model.Account;
import com.api.movementsaccount.model.AccountType;
import com.api.movementsaccount.model.Customer;
import com.api.movementsaccount.repository.AccountRepository;
import com.api.movementsaccount.service.AccountService;
import com.api.movementsaccount.service.dto.AccountDto;
import com.api.movementsaccount.service.dto.MovementDto;
import com.api.movementsaccount.service.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,
                              AccountMapper accountMapper){
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountDto getAccountByAccountNumber(String accountNumber) throws ResourceNotFoundException {
        Optional<Account> accountOptional = accountRepository.getAccountByAccountNumber(accountNumber);
        if(accountOptional.isPresent()){
            return accountMapper.accountToAccountDto(
                    accountOptional.get()
            );
        }else {
            throw new ResourceNotFoundException(MessageMistakes.wasAbleToFindTheResource(accountNumber));
        }
    }

    @Override
    public AccountDto updateInitialBalance(MovementDto movementDto) throws ResourceNotFoundException, MovementException {
        Optional<Account> accountOptional = accountRepository.getAccountByAccountNumber(movementDto.getAccountNumber());
        if(accountOptional.isPresent()){
            Account account = accountOptional.get();
            if(account.getInitialBalance()<movementDto.getValue() && movementDto.getIdTmo().equals("R")){
                throw new MovementException(MessageMistakes.DOES_NOT_HAVE_SUFFICIENT_BALANCE);
            }else {
                account.setInitialBalance(movementDto.getIdTmo().equals("R")?account.getInitialBalance()- movementDto.getValue()
                        : account.getInitialBalance()+movementDto.getValue());
                return accountMapper.accountToAccountDto(
                        accountRepository.save(account)
                );
            }

        }else {
            throw new ResourceNotFoundException(MessageMistakes.wasAbleToFindTheResource(movementDto.getAccountNumber()));
        }
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) throws AtAlreadyExistsException {
        Optional<Account> accountOptional = accountRepository.getAccountByAccountNumber(accountDto.getAccountNumber());
        if(accountOptional.isPresent()){
            throw new AtAlreadyExistsException(MessageMistakes.theAccountNumberAlreadyExists(accountDto.getAccountNumber()));
        }
        return accountMapper.accountToAccountDto(
                accountRepository.save(
                        new Account(null,
                                new Customer(accountDto.getIdCli()),
                                new AccountType(accountDto.getIdTcu()),
                                accountDto.getAccountNumber(),
                                accountDto.getInitialBalance(),
                                Boolean.TRUE
                        )
                )
        );
    }

    @Override
    public AccountDto disableAccountByAccountNumber(String accountNumber) throws ResourceNotFoundException {
        Optional<Account> accountOptional = accountRepository.getAccountByAccountNumber(accountNumber);
        if (accountOptional.isPresent()){
            Account account = accountOptional.get();
            account.setState(Boolean.FALSE);
            return accountMapper.accountToAccountDto(
                    accountRepository.save(account)
            );
        }else {
            throw new ResourceNotFoundException(MessageMistakes.wasAbleToFindTheResource(accountNumber));
        }
    }
}
