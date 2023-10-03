package com.api.personcustomer.integration;

import com.api.personcustomer.model.Customer;
import com.api.personcustomer.model.Gender;
import com.api.personcustomer.model.Person;
import com.api.personcustomer.repository.CustomerRepository;
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

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    private static final String ID = "3EC8F430-A85B-4384-874B-0017D7893AB6";
    private Customer customer;
    private Person person;
    @BeforeEach
    void setUp(){
        person = new Person("id",new Gender("M"),"name",18,"address","0999999999");
        customer = new Customer(ID,person,"*****",Boolean.TRUE);
    }
    @Test
    void getCustomerById() throws Exception {
        Mockito.when(customerRepository.getCustomerByIdentification(Mockito.anyString())).thenReturn(Optional.of(customer));
        mvc.perform(get("/customers/"+ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.when(customerRepository.getCustomerByIdentification(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
        mvc.perform(get("/customers/"+ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isExpectationFailed());
    }
    @Test
    void saveCustomer() throws Exception {
        mvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON)
                .content(JSON)).andExpect(status().is(400));
    }
}
