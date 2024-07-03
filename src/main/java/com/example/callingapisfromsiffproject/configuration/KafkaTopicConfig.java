package com.example.callingapisfromsiffproject.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic newTopic() {
        return TopicBuilder.name("topic1").build();
//        return new NewTopic("topic1", 1, (short) 1);
    }
}
