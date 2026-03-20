package org.example.kjulab1.repository;

import org.example.kjulab1.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Query("SELECT d FROM Driver d WHERE " +
            "(:nationality IS NULL OR d.nationality = :nationality) AND " +
            "(:teamName IS NULL OR d.teamName = :teamName) AND " +
            "(:championships IS NULL OR d.championships >= :championships)")
    Page<Driver> filterDrivers(
            @Param("nationality") String nationality,
            @Param("teamName") String teamName,
            @Param("championships") Integer championships,
            Pageable pageable
    );
}
