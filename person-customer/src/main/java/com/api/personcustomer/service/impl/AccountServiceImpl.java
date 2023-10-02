package com.api.personcustomer.service.impl;

import com.api.personcustomer.exception.ResourceNotFoundException;
import com.api.personcustomer.service.AccountService;
import com.api.personcustomer.service.CustomerService;
import com.api.personcustomer.service.dto.AccountDto;
import com.api.personcustomer.service.dto.GenericMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {
    private final CustomerService customerService;

    @Value("${movements-account.url.service}")
    private String baseUrl;
    private WebClient.Builder webClientBuilder;
    @Autowired
    public AccountServiceImpl(CustomerService customerService,
                              WebClient.Builder webClientBuilder){
        this.customerService = customerService;
        this.webClientBuilder = webClientBuilder;
    }
    @Override
    public AccountDto createAccount(AccountDto accountDto) throws ResourceNotFoundException {
        accountDto.setIdCli(customerService.getCustomerById(accountDto.getIdentification()).getIdCli());
        return createAccountUrlService(accountDto);
    }
    @Override
    public AccountDto disableAccountByAccountNumber(String accountNumber) throws ResourceNotFoundException {
        return disableAccountUrlServiceByAccountNumber(accountNumber);
    }
    private AccountDto createAccountUrlService(AccountDto accountDto) throws ResourceNotFoundException {
        try {
            return webClientBuilder.build().post()
                    .uri(baseUrl+"/movements-account/accounts")
                    .body(Mono.just(accountDto),AccountDto.class)
                    .retrieve()
                    .onStatus(HttpStatus.EXPECTATION_FAILED::equals,response ->
                        response.bodyToMono(GenericMessage.class).flatMap(error ->
                           Mono.error(new Exception(error.getMessage()))
                        )
                    )
                    .bodyToMono(AccountDto.class)
                    .block();
        }catch (Exception e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
    private AccountDto disableAccountUrlServiceByAccountNumber(String accountNumber) throws ResourceNotFoundException {
        try {
            return webClientBuilder.build().patch()
                    .uri(baseUrl+"/movements-account/accounts/disable?accountNumber="+accountNumber)
                    .retrieve()
                    .onStatus(HttpStatus.EXPECTATION_FAILED::equals,response ->
                            response.bodyToMono(GenericMessage.class).flatMap(error ->
                                    Mono.error(new Exception(error.getMessage()))
                            )
                    )
                    .bodyToMono(AccountDto.class)
                    .block();
        }catch (Exception e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
