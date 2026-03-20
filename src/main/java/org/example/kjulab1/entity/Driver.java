package org.example.kjulab1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"first_name", "last_name"}))
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Nationality is required")
    private String nationality;

    @NotBlank(message = "Team name is required")
    private String teamName;

    @Min(value = 1, message = "Driver number must be at least 1")
    @Max(value = 99, message = "Driver number must be at most 99")
    private int driverNumber;

    @Min(value = 0, message = "Championships cannot be negative")
    private int championships;
}