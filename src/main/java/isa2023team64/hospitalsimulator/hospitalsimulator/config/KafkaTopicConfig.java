package isa2023team64.hospitalsimulator.hospitalsimulator.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    
    @Bean
    public NewTopic hospitalTopic() {
        return TopicBuilder.name("contract").build();
    }

}
