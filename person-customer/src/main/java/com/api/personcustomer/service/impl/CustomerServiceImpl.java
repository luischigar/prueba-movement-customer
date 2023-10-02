package com.api.personcustomer.service.impl;

import com.api.personcustomer.exception.AtAlreadyExistsException;
import com.api.personcustomer.exception.ResourceNotFoundException;
import com.api.personcustomer.message.MessageMistakes;
import com.api.personcustomer.model.Customer;
import com.api.personcustomer.repository.CustomerRepository;
import com.api.personcustomer.service.CustomerService;
import com.api.personcustomer.service.PersonService;
import com.api.personcustomer.service.dto.AccountDto;
import com.api.personcustomer.service.dto.CustomerDto;
import com.api.personcustomer.service.dto.GenericMessage;
import com.api.personcustomer.service.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PersonService personService;
    private final CustomerMapper customerMapper;
    @Value("${movements-account.url.service}")
    private String baseUrl;
    private WebClient.Builder webClientBuilder;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               PersonService personService,
                               CustomerMapper customerMapper,
                               WebClient.Builder webClientBuilder){
        this.customerRepository = customerRepository;
        this.personService = personService;
        this.customerMapper = customerMapper;
        this.webClientBuilder = webClientBuilder;
    }
    @Override
    public CustomerDto getCustomerById(String id) throws ResourceNotFoundException {
        Optional<Customer> customerOptional = customerRepository.getCustomerByIdentification(id);
        if(customerOptional.isPresent()){
            return customerMapper.customerToCustomerDto(customerOptional.get());
        }else {
            throw new ResourceNotFoundException(MessageMistakes.wasAbleToFindTheResource(id));
        }
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) throws AtAlreadyExistsException {
        Optional<Customer> customerOptional = customerRepository.getCustomerByIdentification(customerDto.getIdentification());
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            if(Boolean.TRUE.equals(customer.getState())){
                throw new AtAlreadyExistsException(MessageMistakes.itAlreadyExists(customerDto.getIdentification()));
            }
            customer.setState(Boolean.TRUE);
            customer.setPassword(customer.getPassword());
            return customerMapper.customerToCustomerDto(
                    customerRepository.save(customer)
            );
        }
        return customerMapper.customerToCustomerDto(
                customerRepository.save(new Customer(
                        null,
                        personService.savePersonByCustomer(customerDto),
                        customerDto.getPassword(),
                        Boolean.TRUE
                ))
        );
    }

    @Override
    public CustomerDto edictCustomer(CustomerDto customerDto) throws ResourceNotFoundException {
        Optional<Customer> customerOptional = customerRepository.getCustomerByIdentification(customerDto.getIdentification());
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            customer.setPerson(
                    personService.edictPersonByCustomer(customerDto)
            );
            customer.setPassword(customerDto.getPassword());
            return customerMapper.customerToCustomerDto(
                    customerRepository.save(
                            customer
                    )
            );
        }else {
            throw new ResourceNotFoundException(MessageMistakes.wasAbleToFindTheResource(customerDto.getIdentification()));
        }
    }

    @Override
    public CustomerDto disableCustomer(String identification) throws ResourceNotFoundException {
        Optional<Customer> customerOptional = customerRepository.getCustomerByIdentification(identification);
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            List<AccountDto> accountDtoList = getActiveAccountByIdCli(customer.getIdCli())
                    .collect(Collectors.toList())
                    .share().block();
            if(accountDtoList.size() == 0){
                customer.setState(Boolean.FALSE);
                return customerMapper.customerToCustomerDto(
                        customerRepository.save(
                                customer
                        )
                );
            }else {
                throw new ResourceNotFoundException("Dispone de cuentas activas");
            }
        }else {
            throw new ResourceNotFoundException(MessageMistakes.wasAbleToFindTheResource(identification));
        }
    }
    private Flux<AccountDto> getActiveAccountByIdCli(String idCli) throws ResourceNotFoundException {
        try {
            return webClientBuilder.build().get()
                    .uri(baseUrl+"/movements-account/accounts/active-accounts?idCli="+idCli)
                    .retrieve()
                    .onStatus(HttpStatus.EXPECTATION_FAILED::equals, response ->
                            response.bodyToMono(GenericMessage.class).flatMap(error ->
                                    Mono.error(new Exception(error.getMessage()))
                            )
                    )
                    .bodyToFlux(AccountDto.class);
        }catch (Exception e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
