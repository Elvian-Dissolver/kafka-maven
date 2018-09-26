package com.kafkaproducermaven.controllers;

import com.kafkaproducermaven.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class UserControllers {
    @Autowired
    //private KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC = "Kafka_Demo";
}
