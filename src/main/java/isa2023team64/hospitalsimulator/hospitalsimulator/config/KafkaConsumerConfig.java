package isa2023team64.hospitalsimulator.hospitalsimulator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumerConfig {

    @KafkaListener(topics = "hospital", groupId = "Consumer-Group-2")
    public void listenForDeliveryMessages(String message) {
        System.out.println(message);
    }
}
