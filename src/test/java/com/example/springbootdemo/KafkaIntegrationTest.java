package com.example.springbootdemo;

import com.example.springbootdemo.kafka.Consumer;
import com.example.springbootdemo.kafka.Producer;
import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.BEFORE_METHOD;

//@EnableKafka
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@DirtiesContext
//@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@EmbeddedKafka(partitions = 1, topics={"${embedded.kafka.topicsToCreate}"},
		bootstrapServersProperty = "${spring.kafka.bootstrap-servers}" )
@ActiveProfiles("test")
@SpringBootTest
class KafkaIntegrationTest {

	@Autowired
	Producer producer;

	@Autowired
	Consumer consumer;

	@BeforeAll
	public void start(){
		System.out.println("BEFORE TEST !!!!!");
	}

	@AfterAll
	public void finish(){
		System.out.println("AFTER TEST !!!!!");
	}

	@Test
	void sendKafkaMessage() {
		producer.sendMessage("KATYA");
		await().pollInterval(1, TimeUnit.SECONDS)
			   .timeout(10, TimeUnit.SECONDS)
			   .untilAsserted(() -> assertThat(consumer.getCounter()).isEqualTo(1));
	}

}
