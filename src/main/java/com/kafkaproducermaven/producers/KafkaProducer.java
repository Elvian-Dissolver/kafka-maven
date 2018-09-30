package com.kafkaproducermaven.producers;

import com.kafkaproducermaven.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC = "Kafka_Demo";

    @GetMapping("/publish/{name}")
    public String post(@PathVariable("name") final String name){

        kafkaTemplate.send(TOPIC, new User(1,name, "Jakarta", 22));

        return "Published successfully";

    }

}
