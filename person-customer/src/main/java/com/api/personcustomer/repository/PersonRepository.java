package com.api.personcustomer.repository;

import com.api.personcustomer.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,String> {
}
