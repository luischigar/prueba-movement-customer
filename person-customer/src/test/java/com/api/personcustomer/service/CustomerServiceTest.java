package com.api.personcustomer.service;

import com.api.personcustomer.exception.AtAlreadyExistsException;
import com.api.personcustomer.exception.ResourceNotFoundException;
import com.api.personcustomer.message.MessageMistakes;
import com.api.personcustomer.message.MessageSuccess;
import com.api.personcustomer.model.Customer;
import com.api.personcustomer.model.Person;
import com.api.personcustomer.repository.CustomerRepository;
import com.api.personcustomer.service.dto.CustomerDto;
import com.api.personcustomer.service.impl.CustomerServiceImpl;
import com.api.personcustomer.service.mapper.CustomerMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private PersonService personService;
    @Mock
    private CustomerMapper customerMapper;
    @InjectMocks
    private CustomerServiceImpl customerService;
    private static final String ID = "3EC8F430-A85B-4384-874B-0017D7893AB6";
    private Customer customer;
    private CustomerDto customerDto;
    private Person person;
    @BeforeEach
    void setUp(){
        customer = new Customer(ID,person,"*****",Boolean.TRUE);
        customerDto = new CustomerDto(ID,"idemtification","M","name",18,"direccion","0999999999","*******",Boolean.TRUE);
        person = new Person();
    }
    @Test
    void getCustomerById() throws ResourceNotFoundException {
        Mockito.when(customerRepository.getCustomerByIdentification(Mockito.anyString())).thenReturn(Optional.of(customer));
        Mockito.when(customerMapper.customerToCustomerDto(Mockito.any(Customer.class))).thenReturn(customerDto);
        CustomerDto customerDtoData = customerService.getCustomerById(ID);
        Assert.isTrue(customerDtoData.getIdCli().equals(ID), MessageSuccess.SUCCESS);

        Mockito.when(customerRepository.getCustomerByIdentification(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, ()->customerService.getCustomerById(ID));
        Assertions.assertEquals(MessageMistakes.wasAbleToFindTheResource(ID),exception.getMessage());
    }
    @Test
    void saveCustomer() throws AtAlreadyExistsException {
        Mockito.when(customerRepository.getCustomerByIdentification(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
        Mockito.when(personService.savePersonByCustomer(Mockito.any(CustomerDto.class))).thenReturn(person);
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        Mockito.when(customerMapper.customerToCustomerDto(Mockito.any(Customer.class))).thenReturn(customerDto);
        CustomerDto customerDtoData = customerService.saveCustomer(customerDto);
        Assert.isTrue(customerDtoData.getIdCli().equals(ID), MessageSuccess.SUCCESS);

        Mockito.when(customerRepository.getCustomerByIdentification(Mockito.anyString())).thenReturn(Optional.of(customer));
        AtAlreadyExistsException exception = Assertions.assertThrows(AtAlreadyExistsException.class, ()->customerService.saveCustomer(customerDto));
        Assertions.assertEquals(MessageMistakes.itAlreadyExists(customerDto.getIdentification()),exception.getMessage());

        customer.setState(Boolean.FALSE);
        Mockito.when(customerRepository.getCustomerByIdentification(Mockito.anyString())).thenReturn(Optional.of(customer));
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        Mockito.when(customerMapper.customerToCustomerDto(Mockito.any(Customer.class))).thenReturn(customerDto);
        customerDtoData = customerService.saveCustomer(customerDto);
        Assert.isTrue(customerDtoData.getIdCli().equals(ID), MessageSuccess.SUCCESS);
    }
    @Test
    void edictCustomer() throws ResourceNotFoundException {
        Mockito.when(customerRepository.getCustomerByIdentification(Mockito.anyString())).thenReturn(Optional.of(customer));
        Mockito.when(personService.edictPersonByCustomer(Mockito.any(CustomerDto.class))).thenReturn(person);
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        Mockito.when(customerMapper.customerToCustomerDto(Mockito.any(Customer.class))).thenReturn(customerDto);
        CustomerDto customerDtoData = customerService.edictCustomer(customerDto);
        Assert.isTrue(customerDtoData.getIdCli().equals(ID), MessageSuccess.SUCCESS);

        Mockito.when(customerRepository.getCustomerByIdentification(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, ()->customerService.edictCustomer(customerDto));
        Assertions.assertEquals(MessageMistakes.wasAbleToFindTheResource(customerDto.getIdentification()),exception.getMessage());
    }
    @Test
    void disableCustomer(){
        Mockito.when(customerRepository.getCustomerByIdentification(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, ()->customerService.disableCustomer(customerDto.getIdentification()));
        Assertions.assertEquals(MessageMistakes.wasAbleToFindTheResource(customerDto.getIdentification()),exception.getMessage());
    }
}
