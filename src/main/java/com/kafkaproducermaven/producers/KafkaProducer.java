package com.kafkaproducermaven.producers;

import com.kafkaproducermaven.models.User;
import com.kafkaproducermaven.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class KafkaProducer {
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC = "Kafka_Demo";

    @Autowired
    KafkaProducer(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @GetMapping("/publish/{name}")
    public String post(@PathVariable("name") final String name){

        kafkaTemplate.send(TOPIC, new User(1,name, "Jakarta", 22));

        return "Published successfully";

    }

    @PostMapping("/insert")
    public String insert(@RequestBody User user){
        this.userRepository.insert(user);
        kafkaTemplate.send(TOPIC, user);
        user.setAction("Post");
        return "Post successfully";
    }


}
