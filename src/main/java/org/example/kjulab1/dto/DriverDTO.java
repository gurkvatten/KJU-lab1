package org.example.kjulab1.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DriverDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String description;
    private LocalDate dateOfBirth;
    private String nationality;
    private String teamName;
    private int driverNumber;
    private int championships;

}