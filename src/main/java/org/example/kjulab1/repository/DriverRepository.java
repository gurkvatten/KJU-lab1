package org.example.kjulab1.repository;

import org.example.kjulab1.entity.Driver;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

public interface DriverRepository extends ListCrudRepository<Driver, Long> {
    List<Driver> findByNationality(String nationality);
    List<Driver> findByTeamName(String teamName);
    List<Driver> findByChampionshipsGreaterThanEqual(int championships);
}
