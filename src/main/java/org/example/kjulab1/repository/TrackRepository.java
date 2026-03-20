package org.example.kjulab1.repository;

import org.example.kjulab1.entity.Track;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrackRepository extends JpaRepository<Track, Long> {

    @Query("SELECT t FROM Track t WHERE " +
            "(:country IS NULL OR t.country = :country) AND " +
            "(:city IS NULL OR t.city = :city)")
    Page<Track> filterTracks(
            @Param("country") String country,
            @Param("city") String city,
            Pageable pageable
    );
}