package isa2023team64.hospitalsimulator.hospitalsimulator.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import isa2023team64.hospitalsimulator.hospitalsimulator.model.Contract;

@Configuration
public class KafkaProducerConfig {
    
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public Map<String, Object> producerConfig() {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return properties;
    }

    // verovatno Contract umesto drugog stringa
    @Bean
    public ProducerFactory<String, Contract> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    // isto ovde Contract umesto string
    @Bean
    public KafkaTemplate<String, Contract> kafkaTemplate(ProducerFactory<String, Contract> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

}
