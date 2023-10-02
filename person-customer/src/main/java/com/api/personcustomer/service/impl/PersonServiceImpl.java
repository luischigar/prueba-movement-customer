package com.api.personcustomer.service.impl;

import com.api.personcustomer.exception.ResourceNotFoundException;
import com.api.personcustomer.message.MessageMistakes;
import com.api.personcustomer.model.Gender;
import com.api.personcustomer.model.Person;
import com.api.personcustomer.repository.PersonRepository;
import com.api.personcustomer.service.PersonService;
import com.api.personcustomer.service.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    @Autowired
    public PersonServiceImpl(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public Person savePersonByCustomer(CustomerDto customerDto) {
        Optional<Person> personOptional = personRepository.findById(customerDto.getIdentification());
        return personOptional.orElseGet(() -> personRepository.save(
                new Person(customerDto.getIdentification(),
                        new Gender(customerDto.getIdGen()),
                        customerDto.getName(),
                        customerDto.getAge(),
                        customerDto.getAddress(),
                        customerDto.getPhone())
        ));
    }

    @Override
    public Person edictPersonByCustomer(CustomerDto customerDto) throws ResourceNotFoundException {
        Optional<Person> personOptional = personRepository.findById(customerDto.getIdentification());
        if(personOptional.isPresent()){
            Person person = personOptional.get();
            person.setGender(new Gender(customerDto.getIdGen()));
            person.setName(customerDto.getName());
            person.setAge(customerDto.getAge());
            person.setAddress(customerDto.getAddress());
            person.setPhone(customerDto.getPhone());

            return personRepository.save(person);
        }else {
            throw new ResourceNotFoundException(MessageMistakes.wasAbleToFindTheResource(customerDto.getIdentification()));
        }
    }
}
