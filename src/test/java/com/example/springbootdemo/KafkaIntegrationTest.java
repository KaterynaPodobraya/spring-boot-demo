package com.example.springbootdemo;

import com.example.springbootdemo.kafka.Consumer;
import com.example.springbootdemo.kafka.Producer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@EmbeddedKafka(partitions = 1, topics={"${embedded.kafka.topicsToCreate}"},
		bootstrapServersProperty = "${spring.kafka.bootstrap-servers}" )
@ActiveProfiles("test")
@SpringBootTest
class KafkaIntegrationTest {

	@Autowired
	Producer producer;

	@Autowired
	Consumer consumer;

	@BeforeEach
	public void start(){
		System.out.println("BEFORE TEST !!!!!");
	}

	@AfterEach
	public void finish(){
		System.out.println("AFTER TEST !!!!!");
	}

	@Test
	void sendKafkaMessage() throws InterruptedException, ExecutionException {
		Thread.sleep(10000); // does not work locally
		producer.sendMessage("KATYA");
		await().pollInterval(1, TimeUnit.SECONDS)
			   .timeout(30, TimeUnit.SECONDS)
			   .untilAsserted(() -> assertThat(consumer.getCounter()).isEqualTo(1));
	}

}
