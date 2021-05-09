package com.example.springbootdemo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class Consumer {
    private int counter;

    @KafkaListener(topics = "test_topic",groupId = "group_id")
    public void consumeMessage(String message){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + message);
        counter += 1;
    }

    public int getCounter(){
        return counter;
    }
}
