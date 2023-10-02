package com.api.personcustomer.service;

import com.api.personcustomer.exception.AtAlreadyExistsException;
import com.api.personcustomer.exception.ResourceNotFoundException;
import com.api.personcustomer.service.dto.CustomerDto;

public interface CustomerService {
    CustomerDto getCustomerById(String id) throws ResourceNotFoundException;
    CustomerDto saveCustomer(CustomerDto customerDto) throws AtAlreadyExistsException;
    CustomerDto edictCustomer(CustomerDto customerDto) throws ResourceNotFoundException;
    CustomerDto disableCustomer(String identification) throws ResourceNotFoundException;
}
