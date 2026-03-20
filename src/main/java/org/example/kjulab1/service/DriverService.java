package org.example.kjulab1.service;

import org.example.kjulab1.dto.CreateDriverDTO;
import org.example.kjulab1.dto.DriverDTO;
import org.example.kjulab1.dto.UpdateDriverDTO;
import org.example.kjulab1.entity.Driver;
import org.example.kjulab1.exception.ResourceNotFoundException;
import org.example.kjulab1.mapper.DriverMapper;
import org.example.kjulab1.repository.DriverRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                .orElseThrow(() -> new ResourceNotFoundException("Driver with id " + id + " not found"));
        return driverMapper.toDTO(driver);
    }

    public DriverDTO createDriver(CreateDriverDTO dto) {
        Driver driver = driverMapper.toEntity(dto);
        return driverMapper.toDTO(driverRepository.save(driver));
    }

    public DriverDTO updateDriver(Long id, UpdateDriverDTO dto) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver with id " + id + " not found"));
        driverMapper.updateEntity(dto, driver);
        return driverMapper.toDTO(driverRepository.save(driver));
    }
    public Page<DriverDTO> filterDrivers(String nationality, String teamName, Integer championships, Pageable pageable) {
        return driverRepository.filterDrivers(nationality, teamName, championships, pageable)
                .map(driverMapper::toDTO);
    }


    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}