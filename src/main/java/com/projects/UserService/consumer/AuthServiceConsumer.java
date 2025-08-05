package com.projects.UserService.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.UserService.entities.UserInfoDto;
import com.projects.UserService.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceConsumer
{

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(UserInfoDto eventData) {
        try{
            // Todo: Make it transactional, to handle idempotency and validate email, phoneNumber etc
            userService.createOrUpdateUser(eventData);
        } catch(Exception ex){
        System.err.println("!!!!!!!! KAFKA CONSUMER FAILED TO PROCESS MESSAGE !!!!!!!");
        ex.printStackTrace(System.err);
        }
    }

}
