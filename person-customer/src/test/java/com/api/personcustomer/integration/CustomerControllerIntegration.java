package com.api.personcustomer.integration;

import com.api.personcustomer.model.Customer;
import com.api.personcustomer.model.Gender;
import com.api.personcustomer.model.Person;
import com.api.personcustomer.repository.CustomerRepository;
import com.api.personcustomer.service.dto.CustomerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class CustomerControllerIntegration {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CustomerRepository customerRepository;
    private static final String JSON = "{\n" +
            "    \"identification\": \"hola\",\n" +
            "    \"idGen\": \"MM\",\n" +
            "    \"name\": \"hola\",\n" +
            "    \"age\": 18,\n" +
            "    \"address\": \"hola\",\n" +
            "    \"phone\": \"hola\",\n" +
            "    \"password\": \"hola\"\n" +
            "}";
    private final ObjectMapper mapperJson = new ObjectMapper();
    private static final String ID = "3EC8F430-A85B-4384-874B-0017D7893AB6";
    private Customer customer;
    private CustomerDto customerDto;
    private Person person;
    @BeforeEach
    void setUp(){
        customerDto = new CustomerDto(null,"hola","MM","hola",18,"hola","hola","hola",Boolean.TRUE);
        person = new Person("id",new Gender("M"),"name",18,"address","0999999999");
        customer = new Customer(ID,person,"*****",Boolean.TRUE);
    }
    @Test
    void getCustomerById() throws Exception {
        Mockito.when(customerRepository.getCustomerByIdentification(Mockito.anyString())).thenReturn(Optional.of(customer));
        mvc.perform(MockMvcRequestBuilders.get("/customers/"+ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Mockito.when(customerRepository.getCustomerByIdentification(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
        mvc.perform(MockMvcRequestBuilders.get("/customers/"+ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isExpectationFailed());
    }
    @Test
    void saveCustomer() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/customers").contentType(MediaType.APPLICATION_JSON)
                .content(mapperJson.writeValueAsString(customerDto))).andExpect(MockMvcResultMatchers.status().is(400));
    }
}
