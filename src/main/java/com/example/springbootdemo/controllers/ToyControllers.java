package com.example.springbootdemo.controllers;

import com.example.springbootdemo.domain.Toy;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class ToyControllers {
    @Value("${toy.shop}")
    String shop;

    @GetMapping(path = "/getToy")
    public Toy getToyById(){
        log.trace("Starting writing TRACE logs.");
        log.debug("Starting writing DEBUG logs.");
        log.info("Starting writing INFO logs.");
        log.warn("Starting writing WARN logs.");
        log.error("Starting writing ERROR logs.");
        System.out.println(shop);
        return new Toy("Bobik", "Multicolor", "big", 3);
    }

}
