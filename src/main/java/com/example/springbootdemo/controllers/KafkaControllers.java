package com.example.springbootdemo.controllers;

import com.example.springbootdemo.domain.Toy;
import com.example.springbootdemo.kafka.Producer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Log4j2
@RestController
public class KafkaControllers {

    @Autowired
    public Producer producer;

    @PostMapping(path = "/sendToy")
    public void sendToyToKafka() throws ExecutionException, InterruptedException {
        Toy toy = new Toy("Kot", "Multicolor", "middle", 6);
        producer.sendMessage(toy.toString());
    }
}
