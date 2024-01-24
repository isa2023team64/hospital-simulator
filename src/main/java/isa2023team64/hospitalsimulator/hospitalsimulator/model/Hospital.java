package isa2023team64.hospitalsimulator.hospitalsimulator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hospital {
    
    private int id;
    private String name;
    private double longitude;
    private double latitude;

}
