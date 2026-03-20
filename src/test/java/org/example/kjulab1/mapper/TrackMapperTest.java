package org.example.kjulab1.mapper;

import org.example.kjulab1.dto.CreateTrackDTO;
import org.example.kjulab1.dto.TrackDTO;
import org.example.kjulab1.dto.UpdateTrackDTO;
import org.example.kjulab1.entity.Track;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrackMapperTest {

    private final TrackMapper mapper = new TrackMapper();

    @Test
    void toEntity_shouldMapCreateDTOToTrack() {
        CreateTrackDTO dto = new CreateTrackDTO();
        dto.setName("Monza");
        dto.setDescription("Temple of Speed");
        dto.setCountry("Italy");
        dto.setCity("Monza");
        dto.setLapRecord("1:21.046");
        dto.setNumberOfCorners(11);
        dto.setCircuitLengthKm(5.793);

        Track track = mapper.toEntity(dto);

        assertEquals("Monza", track.getName());
        assertEquals("Italy", track.getCountry());
        assertEquals(11, track.getNumberOfCorners());
        assertEquals(5.793, track.getCircuitLengthKm());
    }

    @Test
    void toDTO_shouldMapTrackToTrackDTO() {
        Track track = new Track();
        track.setId(1L);
        track.setName("Monza");
        track.setDescription("Temple of Speed");
        track.setCountry("Italy");
        track.setCity("Monza");
        track.setLapRecord("1:21.046");
        track.setNumberOfCorners(11);
        track.setCircuitLengthKm(5.793);

        TrackDTO dto = mapper.toDTO(track);

        assertEquals(1L, dto.getId());
        assertEquals("Monza", dto.getName());
        assertEquals("Italy", dto.getCountry());
        assertEquals(5.793, dto.getCircuitLengthKm());
    }

    @Test
    void updateEntity_shouldUpdateTrackFromUpdateDTO() {
        Track track = new Track();
        track.setName("Monza");
        track.setCountry("Italy");

        UpdateTrackDTO dto = new UpdateTrackDTO();
        dto.setName("Monza");
        dto.setDescription("Updated description");
        dto.setCountry("Italy");
        dto.setCity("Monza");
        dto.setLapRecord("1:20.000");
        dto.setNumberOfCorners(11);
        dto.setCircuitLengthKm(5.793);

        mapper.updateEntity(dto, track);

        assertEquals("1:20.000", track.getLapRecord());
        assertEquals("Updated description", track.getDescription());
    }
}