package com.example.springbootdemo;

import com.example.springbootdemo.controllers.ToyControllers;
import com.example.springbootdemo.kafka.Producer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(value={ToyControllers.class})
public class ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Disabled
    @SneakyThrows
    @Test
    public void shouldReturnRecord(){
        mockMvc.perform(get("/getToy").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Bobik")));
    }

}
