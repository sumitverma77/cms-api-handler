package com.example.callingapisfromsiffproject.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class KafkaProducer {
    private static final Logger logger = Logger.getLogger(KafkaProducer.class.getName());
    private KafkaTemplate<String, String> kafkaTemplate;
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(String topic, String message) {
        logger.info("Sending message: " + message);
        System.out.println("sending msg from kafka producer ");
        kafkaTemplate.send(topic, message);
    }

}
