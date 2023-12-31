package com.api.personcustomer.integration;

import com.api.personcustomer.PersonCustomerApplication;
import com.api.personcustomer.model.Customer;
import com.api.personcustomer.model.Gender;
import com.api.personcustomer.model.Person;
import com.api.personcustomer.repository.CustomerRepository;
import com.api.personcustomer.service.dto.CustomerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = PersonCustomerApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integration-test.properties")
@ExtendWith(SpringExtension.class)
class CustomerControllerIntegration {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CustomerRepository customerRepository;
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
                .andExpect(MockMvcResultMatchers.status().isExpectationFailed())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is("417 EXPECTATION_FAILED")));
    }
    @Test
    void saveCustomer() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapperJson.writeValueAsString(customerDto)))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }
}
