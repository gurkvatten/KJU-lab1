package org.example.kjulab1.mapper;

import org.example.kjulab1.dto.CreateTrackDTO;
import org.example.kjulab1.dto.TrackDTO;
import org.example.kjulab1.dto.UpdateTrackDTO;
import org.example.kjulab1.entity.Track;
import org.springframework.stereotype.Component;

@Component
public class TrackMapper {

    public Track toEntity(CreateTrackDTO dto) {
        Track track = new Track();
        track.setName(dto.getName());
        track.setDescription(dto.getDescription());
        track.setCountry(dto.getCountry());
        track.setCity(dto.getCity());
        track.setLapRecord(dto.getLapRecord());
        track.setNumberOfCorners(dto.getNumberOfCorners());
        track.setCircuitLengthKm(dto.getCircuitLengthKm());
        return track;
    }

    public void updateEntity(UpdateTrackDTO dto, Track track) {
        track.setName(dto.getName());
        track.setDescription(dto.getDescription());
        track.setCountry(dto.getCountry());
        track.setCity(dto.getCity());
        track.setLapRecord(dto.getLapRecord());
        track.setNumberOfCorners(dto.getNumberOfCorners());
        track.setCircuitLengthKm(dto.getCircuitLengthKm());
    }

    public TrackDTO toDTO(Track track) {
        TrackDTO dto = new TrackDTO();
        dto.setId(track.getId());
        dto.setName(track.getName());
        dto.setDescription(track.getDescription());
        dto.setCountry(track.getCountry());
        dto.setCity(track.getCity());
        dto.setLapRecord(track.getLapRecord());
        dto.setNumberOfCorners(track.getNumberOfCorners());
        dto.setCircuitLengthKm(track.getCircuitLengthKm());
        return dto;
    }
}