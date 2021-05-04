package com.example.springbootdemo;

import lombok.SneakyThrows;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;

import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;

// runs on started application

public class e2eMainTest {

    @Disabled
    @SneakyThrows
    @Test
    public void shouldReturnToy(){
        String body =
                when().get( new URI("/getToy"))
        .then().extract().body().asString();

        assertThat(body).contains("Bobik");

    }

}
