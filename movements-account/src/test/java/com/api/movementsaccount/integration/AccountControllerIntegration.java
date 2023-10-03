package com.api.movementsaccount.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class AccountControllerIntegration {
    @Autowired
    private MockMvc mvc;
    private static final String JSON = "{\n" +
            "    \"idCue\": \"hola\",\n" +
            "    \"idCli\": \"hola\",\n" +
            "    \"idTcu\": \"MM\",\n" +
            "    \"accountNumber\": \"holagggggggg\",\n" +
            "    \"initialBalance\": 18\n" +
            "}";
    @Test
    void saveAccount() throws Exception {
        mvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON)
                .content(JSON)).andExpect(status().is(400));
    }
}
