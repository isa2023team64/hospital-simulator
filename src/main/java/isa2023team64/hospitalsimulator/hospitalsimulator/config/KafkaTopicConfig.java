package isa2023team64.hospitalsimulator.hospitalsimulator.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    
    @Bean
    public NewTopic hospitalTopic() {
        Map<String, String> configs = new HashMap<>();
        
        // Set custom properties for the topic
        // For example, you can set log.retention.ms, but it's not a direct replacement for per-message expiration
        configs.put("log.retention.ms", "2000"); // Set log retention to 2 seconds

        return TopicBuilder.name("contract")
                .configs(configs)
                .build();
    }

}
