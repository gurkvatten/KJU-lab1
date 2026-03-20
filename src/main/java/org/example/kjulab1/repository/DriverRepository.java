package org.example.kjulab1.repository;

import org.example.kjulab1.entity.Driver;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DriverRepository extends ListCrudRepository<Driver, Long> {

    @Query("SELECT d FROM Driver d WHERE " +
            "(:nationality IS NULL OR d.nationality = :nationality) AND " +
            "(:teamName IS NULL OR d.teamName = :teamName) AND " +
            "(:championships IS NULL OR d.championships >= :championships)")
    List<Driver> filterDrivers(
            @Param("nationality") String nationality,
            @Param("teamName") String teamName,
            @Param("championships") Integer championships
    );
}
