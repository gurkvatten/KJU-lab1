package org.example.kjulab1.service;

import org.example.kjulab1.dto.CreateDriverDTO;
import org.example.kjulab1.dto.DriverDTO;
import org.example.kjulab1.dto.UpdateDriverDTO;
import org.example.kjulab1.entity.Driver;
import org.example.kjulab1.exception.ResourceNotFoundException;
import org.example.kjulab1.mapper.DriverMapper;
import org.example.kjulab1.repository.DriverRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverServiceTest {

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private DriverMapper driverMapper;

    @InjectMocks
    private DriverService driverService;

    @Test
    void getAllDrivers_shouldReturnListOfDriverDTOs() {
        Driver driver = new Driver();
        DriverDTO dto = new DriverDTO();
        when(driverRepository.findAll()).thenReturn(List.of(driver));
        when(driverMapper.toDTO(driver)).thenReturn(dto);

        List<DriverDTO> result = driverService.getAllDrivers();

        assertEquals(1, result.size());
        verify(driverRepository).findAll();
    }

    @Test
    void getDriverById_shouldReturnDriverDTO() {
        Driver driver = new Driver();
        DriverDTO dto = new DriverDTO();
        when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));
        when(driverMapper.toDTO(driver)).thenReturn(dto);

        DriverDTO result = driverService.getDriverById(1L);

        assertNotNull(result);
        verify(driverRepository).findById(1L);
    }

    @Test
    void getDriverById_shouldThrowWhenNotFound() {
        when(driverRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> driverService.getDriverById(999L));
    }

    @Test
    void createDriver_shouldSaveAndReturnDriverDTO() {
        CreateDriverDTO createDTO = new CreateDriverDTO();
        Driver driver = new Driver();
        DriverDTO driverDTO = new DriverDTO();

        when(driverMapper.toEntity(createDTO)).thenReturn(driver);
        when(driverRepository.save(driver)).thenReturn(driver);
        when(driverMapper.toDTO(driver)).thenReturn(driverDTO);

        DriverDTO result = driverService.createDriver(createDTO);

        assertNotNull(result);
        verify(driverRepository).save(driver);
    }

    @Test
    void deleteDriver_shouldCallRepository() {
        driverService.deleteDriver(1L);
        verify(driverRepository).deleteById(1L);
    }

    @Test
    void updateDriver_shouldThrowWhenNotFound() {
        when(driverRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> driverService.updateDriver(999L, new UpdateDriverDTO()));
    }
}