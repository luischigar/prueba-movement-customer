package com.api.movementsaccount.service;

import com.api.movementsaccount.exception.ResourceNotFoundException;
import com.api.movementsaccount.message.MessageMistakes;
import com.api.movementsaccount.message.MessageSuccess;
import com.api.movementsaccount.model.Account;
import com.api.movementsaccount.repository.AccountRepository;
import com.api.movementsaccount.service.dto.AccountDto;
import com.api.movementsaccount.service.impl.AccountServiceImpl;
import com.api.movementsaccount.service.mapper.AccountMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountMapper accountMapper;
    @InjectMocks
    private AccountServiceImpl accountService;
    private static final String ID = "3EC8F430-A85B-4384-874B-0017D7893AB6";
    private List<Account> accountList;
    @BeforeEach
    void setUp(){
        accountList = new ArrayList<>();
        accountList.add(new Account(ID));
    }
    @Test
    void getActiveAccountsByIdCli() throws ResourceNotFoundException {
        Mockito.when(accountRepository.getActiveAccountsByIdCli(Mockito.anyString())).thenReturn(accountList);
        Mockito.when(accountMapper.accountToAccountDto(Mockito.any(Account.class))).thenReturn(new AccountDto());
        List<AccountDto> accountDtoListData = accountService.getActiveAccountsByIdCli(ID);
        Assert.isTrue(accountDtoListData.size()>0, MessageSuccess.SUCCESS);

        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, ()->accountService.getActiveAccountsByIdCli(""));
        Assertions.assertEquals(MessageMistakes.NULL_OR_EMPTY,exception.getMessage());
    }
}
