package com.api.personcustomer.service.mapper;

import com.api.personcustomer.model.Customer;
import com.api.personcustomer.service.dto.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    CustomerDto customerToCustomerDto(Customer customer);
}
