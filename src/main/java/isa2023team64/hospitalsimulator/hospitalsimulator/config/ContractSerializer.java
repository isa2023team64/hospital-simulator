package isa2023team64.hospitalsimulator.hospitalsimulator.config;

import java.nio.charset.StandardCharsets;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import isa2023team64.hospitalsimulator.hospitalsimulator.model.Contract;

public class ContractSerializer implements Serializer<Contract> {
    private String encoding = StandardCharsets.UTF_8.name();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, Contract data) {
        try {
            if (data == null)
                return null;
            else {
                // String output = "";
                // output += data.getHospitalId();
                // return output.getBytes(encoding);
                return objectMapper.writeValueAsString(data).getBytes();
            }
        } catch (Exception e) {
            throw new SerializationException("Error when serializing string to byte[] due to unsupported encoding " + encoding);
        }
    }
    
}
