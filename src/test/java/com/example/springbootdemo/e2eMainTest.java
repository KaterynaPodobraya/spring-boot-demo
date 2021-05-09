package com.example.springbootdemo;

import io.restassured.RestAssured;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.net.URI;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class e2eMainTest {
    @LocalServerPort
    private int port;

    @Test
    public void shouldReturnToy() throws URISyntaxException {
        String body = given().port(port)
                             .when().get( new URI("/getToy"))
                             .then().extract().body().asString();

        assertThat(body).contains("Bobik");

    }

}
