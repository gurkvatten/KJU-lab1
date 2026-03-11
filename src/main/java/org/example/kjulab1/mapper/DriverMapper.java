package org.example.kjulab1.mapper;

import org.example.kjulab1.dto.CreateDriverDTO;
import org.example.kjulab1.dto.DriverDTO;
import org.example.kjulab1.dto.UpdateDriverDTO;
import org.example.kjulab1.entity.Driver;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {

    public Driver toEntity(CreateDriverDTO dto) {
        Driver driver = new Driver();
        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setDescription(dto.getDescription());
        driver.setDateOfBirth(dto.getDateOfBirth());
        driver.setNationality(dto.getNationality());
        driver.setTeamName(dto.getTeamName());
        driver.setDriverNumber(dto.getDriverNumber());
        driver.setChampionships(dto.getChampionships());
        return driver;
    }

    public void updateEntity(UpdateDriverDTO dto, Driver driver) {
        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setDescription(dto.getDescription());
        driver.setDateOfBirth(dto.getDateOfBirth());
        driver.setNationality(dto.getNationality());
        driver.setTeamName(dto.getTeamName());
        driver.setDriverNumber(dto.getDriverNumber());
        driver.setChampionships(dto.getChampionships());
    }

    public DriverDTO toDTO(Driver driver) {
        DriverDTO dto = new DriverDTO();
        dto.setId(driver.getId());
        dto.setFirstName(driver.getFirstName());
        dto.setLastName(driver.getLastName());
        dto.setDescription(driver.getDescription());
        dto.setDateOfBirth(driver.getDateOfBirth());
        dto.setNationality(driver.getNationality());
        dto.setTeamName(driver.getTeamName());
        dto.setDriverNumber(driver.getDriverNumber());
        dto.setChampionships(driver.getChampionships());
        return dto;
    }
}