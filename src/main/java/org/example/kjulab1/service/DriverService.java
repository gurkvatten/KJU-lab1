package org.example.kjulab1.service;

import org.example.kjulab1.dto.CreateDriverDTO;
import org.example.kjulab1.dto.DriverDTO;
import org.example.kjulab1.dto.UpdateDriverDTO;
import org.example.kjulab1.entity.Driver;
import org.example.kjulab1.mapper.DriverMapper;
import org.example.kjulab1.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    public DriverService(DriverRepository driverRepository, DriverMapper driverMapper) {
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
    }

    public List<DriverDTO> getAllDrivers() {
        return driverRepository.findAll()
                .stream()
                .map(driverMapper::toDTO)
                .toList();
    }

    public DriverDTO getDriverById(Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        return driverMapper.toDTO(driver);
    }

    public DriverDTO createDriver(CreateDriverDTO dto) {
        Driver driver = driverMapper.toEntity(dto);
        return driverMapper.toDTO(driverRepository.save(driver));
    }

    public DriverDTO updateDriver(Long id, UpdateDriverDTO dto) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        driverMapper.updateEntity(dto, driver);
        return driverMapper.toDTO(driverRepository.save(driver));
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}