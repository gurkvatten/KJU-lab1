package org.example.kjulab1.dto;

import lombok.Data;

@Data
public class TrackDTO {

    private Long id;
    private String name;
    private String description;
    private String country;
    private String city;
    private String lapRecord;
    private int numberOfCorners;
    private double circuitLengthKm;
}