package com.example.kafkaImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;


// needs 2 commands to run kafka server
//		 bin/zookeeper-server-start.sh config/zookeeper.properties
//		 bin/kafka-server-start.sh config/server.properties

@SpringBootApplication
public class KafkaImplApplication implements ApplicationRunner {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String msg) {
		kafkaTemplate.send("Hello-Kafka", msg);
	}
	public static void main(String[] args) {
		SpringApplication.run(KafkaImplApplication.class, args);
	}
	@KafkaListener(topics = "Hello-Kafka", groupId = "group-id")
	public void listen(String message) {
		System.out.println("Received Messasge in group - group-id: " + message);
	}
	@Override
	public void run(ApplicationArguments args) throws Exception {
		sendMessage("Hi Welcome to Spring For Apache Kafka");
	}
}
