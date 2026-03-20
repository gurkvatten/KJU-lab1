package org.example.kjulab1.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateTrackDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Lap record is required")
    private String lapRecord;

    @Min(value = 1, message = "Number of corners must be at least 1")
    private int numberOfCorners;

    @DecimalMin(value = "0.1", message = "Circuit length must be at least 0.1 km")
    private double circuitLengthKm;
}