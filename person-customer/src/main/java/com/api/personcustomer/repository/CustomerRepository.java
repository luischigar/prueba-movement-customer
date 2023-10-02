package com.api.personcustomer.repository;

import com.api.personcustomer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query("Select c from Customer c where c.person.identification = ?1")
    Optional<Customer> getCustomerByIdentification(String identification);
}
