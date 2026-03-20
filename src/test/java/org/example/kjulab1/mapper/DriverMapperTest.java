package org.example.kjulab1.mapper;

import org.example.kjulab1.dto.CreateDriverDTO;
import org.example.kjulab1.dto.DriverDTO;
import org.example.kjulab1.dto.UpdateDriverDTO;
import org.example.kjulab1.entity.Driver;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DriverMapperTest {

    private final DriverMapper mapper = new DriverMapper();

    @Test
    void toEntity_shouldMapCreateDTOToDriver() {
        CreateDriverDTO dto = new CreateDriverDTO();
        dto.setFirstName("Lewis");
        dto.setLastName("Hamilton");
        dto.setDescription("Seven-time champion");
        dto.setDateOfBirth(LocalDate.of(1985, 1, 7));
        dto.setNationality("British");
        dto.setTeamName("Ferrari");
        dto.setDriverNumber(44);
        dto.setChampionships(7);

        Driver driver = mapper.toEntity(dto);

        assertEquals("Lewis", driver.getFirstName());
        assertEquals("Hamilton", driver.getLastName());
        assertEquals("British", driver.getNationality());
        assertEquals(44, driver.getDriverNumber());
        assertEquals(7, driver.getChampionships());
    }

    @Test
    void toDTO_shouldMapDriverToDriverDTO() {
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setFirstName("Max");
        driver.setLastName("Verstappen");
        driver.setDescription("Four-time champion");
        driver.setDateOfBirth(LocalDate.of(1997, 9, 30));
        driver.setNationality("Dutch");
        driver.setTeamName("Red Bull Racing");
        driver.setDriverNumber(1);
        driver.setChampionships(4);

        DriverDTO dto = mapper.toDTO(driver);

        assertEquals(1L, dto.getId());
        assertEquals("Max", dto.getFirstName());
        assertEquals("Dutch", dto.getNationality());
        assertEquals(1, dto.getDriverNumber());
    }

    @Test
    void updateEntity_shouldUpdateDriverFromUpdateDTO() {
        Driver driver = new Driver();
        driver.setFirstName("Max");
        driver.setTeamName("Red Bull Racing");

        UpdateDriverDTO dto = new UpdateDriverDTO();
        dto.setFirstName("Max");
        dto.setLastName("Verstappen");
        dto.setDescription("Updated description");
        dto.setDateOfBirth(LocalDate.of(1997, 9, 30));
        dto.setNationality("Dutch");
        dto.setTeamName("Ferrari");
        dto.setDriverNumber(1);
        dto.setChampionships(5);

        mapper.updateEntity(dto, driver);

        assertEquals("Ferrari", driver.getTeamName());
        assertEquals(5, driver.getChampionships());
    }
}