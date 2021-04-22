package com.example.library;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.library.dtos.AuthorDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaReceiver {
	
	@Value("${spring.kafka.consumer.bootstrap-servers}")
	private String kafkaServer;
	
	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;
	
	@Value("${kafka.topic.name}")
	private String topicName;


	@KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void recieveData(AuthorDto author)
	{
		log.info("Data - " + author.toString() + " recieved");
	}
}
