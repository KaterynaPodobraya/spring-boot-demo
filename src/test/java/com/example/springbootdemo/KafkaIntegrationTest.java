package com.example.springbootdemo;

import com.example.springbootdemo.kafka.Consumer;
import com.example.springbootdemo.kafka.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@DirtiesContext
//@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@EmbeddedKafka(partitions = 1, brokerProperties = { "${spring.kafka.bootstrap-servers}" })
@ActiveProfiles("test")
@SpringBootTest
class KafkaIntegrationTest {

	@Autowired
	Producer producer;

	@Autowired
	Consumer consumer;

	@Test
	void sendKafkaMessage() {
		producer.sendMessage("Integration test");
//		consumer.consumeMessage();
	}

}
