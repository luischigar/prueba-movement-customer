package com.api.personcustomer.controller;

import com.api.personcustomer.exception.AtAlreadyExistsException;
import com.api.personcustomer.exception.ResourceNotFoundException;
import com.api.personcustomer.service.CustomerService;
import com.api.personcustomer.service.dto.CustomerDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*")
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody CustomerDto customerDto) throws AtAlreadyExistsException {
        return new ResponseEntity<>(customerService.saveCustomer(customerDto),HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<?> edictCustomer(@Valid @RequestBody CustomerDto customerDto) throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.edictCustomer(customerDto),HttpStatus.OK);
    }
    @PatchMapping("/disable")
    public ResponseEntity<?> disableCustomer(String id) throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.disableCustomer(id),HttpStatus.OK);
    }
}
