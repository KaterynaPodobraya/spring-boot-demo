package com.example.springbootdemo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class Producer {
    private static final String TOPIC = "test_topic";
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String message) throws InterruptedException, ExecutionException {
        log.info(String.format("Producing message: %s", message));
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC, message);
        final SendResult<String, String> stringStringSendResult = future.get();
        log.info(String.format("RESULT : %s", stringStringSendResult));

    }

    @Bean
    public NewTopic createTopic(){
        return new NewTopic(TOPIC,1,(short) 1);
    }
}
