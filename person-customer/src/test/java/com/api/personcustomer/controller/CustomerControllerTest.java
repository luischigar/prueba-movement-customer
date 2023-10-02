package com.api.personcustomer.controller;

import com.api.personcustomer.exception.AtAlreadyExistsException;
import com.api.personcustomer.exception.ResourceNotFoundException;
import com.api.personcustomer.message.MessageSuccess;
import com.api.personcustomer.service.CustomerService;
import com.api.personcustomer.service.dto.CustomerDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerController customerController;
    private static final String ID = "ID";
    @Test
    void getCustomerById() throws ResourceNotFoundException {
        Mockito.when(customerService.getCustomerById(Mockito.anyString())).thenReturn(new CustomerDto());
        ResponseEntity<?> responseEntity = customerController.getCustomerById(ID);
        Assert.isTrue(responseEntity.getStatusCode() == HttpStatus.OK, MessageSuccess.SUCCESS);
    }
    @Test
    void saveCustomer() throws AtAlreadyExistsException {
        Mockito.when(customerService.saveCustomer(Mockito.any(CustomerDto.class))).thenReturn(new CustomerDto());
        ResponseEntity<?> responseEntity = customerController.saveCustomer(new CustomerDto());
        Assert.isTrue(responseEntity.getStatusCode() == HttpStatus.CREATED, MessageSuccess.SUCCESS);
    }
    @Test
    void edictCustomer() throws ResourceNotFoundException {
        Mockito.when(customerService.edictCustomer(Mockito.any(CustomerDto.class))).thenReturn(new CustomerDto());
        ResponseEntity<?> responseEntity = customerController.edictCustomer(new CustomerDto());
        Assert.isTrue(responseEntity.getStatusCode() == HttpStatus.OK, MessageSuccess.SUCCESS);
    }
    @Test
    void disableCustomer() throws ResourceNotFoundException {
        Mockito.when(customerService.disableCustomer(Mockito.anyString())).thenReturn(new CustomerDto());
        ResponseEntity<?> responseEntity = customerController.disableCustomer(ID);
        Assert.isTrue(responseEntity.getStatusCode() == HttpStatus.OK, MessageSuccess.SUCCESS);
    }
}
