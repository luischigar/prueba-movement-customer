package com.api.personcustomer.service.mapper.impl;

import com.api.personcustomer.model.Customer;
import com.api.personcustomer.service.dto.CustomerDto;
import com.api.personcustomer.service.mapper.CustomerMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements CustomerMapper {
    @Override
    public CustomerDto customerToCustomerDto(Customer customer) {
        return new CustomerDto(customer.getIdCli(),
                customer.getPerson().getIdentification(),
                customer.getPerson().getGender().getIdGen(),
                customer.getPerson().getName(),
                customer.getPerson().getAge(),
                customer.getPerson().getAddress(),
                customer.getPerson().getPhone(),
                "*********",
                customer.getState());
    }
}
