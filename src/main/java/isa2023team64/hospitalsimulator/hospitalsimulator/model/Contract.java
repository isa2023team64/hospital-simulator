package isa2023team64.hospitalsimulator.hospitalsimulator.model;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class Contract {
    
    @JsonProperty("hospitalId")
    private int hospitalId;

    @JsonProperty("orders")
    private Collection<Order> orders;

    @JsonProperty("day")
    private int day;

    @Override
    public String toString() {
        String output = "";
        output += "hospitalId: " + hospitalId + ", orders: ";
        for (var order : orders) {
            output += order.getEquipmentId() + "-" + order.getQuantity() + ", ";
        }
        output += "day: " + day;
        return output;
    }

}
