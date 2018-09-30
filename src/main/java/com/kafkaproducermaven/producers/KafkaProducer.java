package com.kafkaproducermaven.producers;

import com.kafkaproducermaven.models.User;
import com.kafkaproducermaven.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("kafka")
public class KafkaProducer {
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC = "Kafka_Demo";
    //private User user;

    @Autowired
    KafkaProducer(UserRepository userRepository, User user){
        this.userRepository=userRepository;
        //this.user = user;
    }

    @GetMapping("/publish/{name}")
    public String post(@PathVariable("name") final String name){
        kafkaTemplate.send(TOPIC, new User(1,name, "Jakarta", 22));
        return "Published successfully";
    }

    @PostMapping("/insert")
    public String insert(@RequestBody User user){
        this.userRepository.insert(user);
        user.setAction("Post");
        kafkaTemplate.send(TOPIC, user);
        return "Post successfully";
    }

    @PutMapping("/update")
    public String update(@RequestBody User user){
        this.userRepository.save(user);
        user.setAction("Update");
        kafkaTemplate.send(TOPIC, user);
        return "Update successfully";
    }

    @GetMapping(value = "/delete/{id}")
    public String remove(@PathVariable int id, User user){
        user = this.userRepository.findById(id);
        user.setAction("Delete");
        kafkaTemplate.send(TOPIC, user);
        this.userRepository.deleteById(id);
        return "Delete successfully";
    }
}
