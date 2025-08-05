package com.projects.UserService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);

    // NOTE: We changed UserInfo to String to see the raw message
    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        LOGGER.info("<<<<< Raw message received from Kafka: {} >>>>>", message);

        // For now, we are not processing the message, just printing it.
        // Once we confirm the format, we will add the logic back.
    }
}