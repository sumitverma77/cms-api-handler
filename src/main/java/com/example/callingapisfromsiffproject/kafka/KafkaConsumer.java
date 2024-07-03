package com.example.callingapisfromsiffproject.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class KafkaConsumer {
    private static final Logger logger = Logger.getLogger(KafkaConsumer.class.getName());
    @KafkaListener(topics = "topic1" , groupId = "myGroup")
    public void reciveMessage(String message) {
        logger.info("Receiving msgs from kafka broker: " +message);
        System.out.println("Receiving the msg from kafka Broker");
        System.out.println(message);
       logger.info(message);
    }
}
