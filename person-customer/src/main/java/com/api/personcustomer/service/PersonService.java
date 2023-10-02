package com.api.personcustomer.service;

import com.api.personcustomer.exception.ResourceNotFoundException;
import com.api.personcustomer.model.Person;
import com.api.personcustomer.service.dto.CustomerDto;

public interface PersonService {
    Person savePersonByCustomer(CustomerDto customerDto);
    Person edictPersonByCustomer(CustomerDto customerDto) throws ResourceNotFoundException;
}
