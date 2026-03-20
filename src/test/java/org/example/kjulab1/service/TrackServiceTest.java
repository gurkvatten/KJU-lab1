package org.example.kjulab1.service;

import org.example.kjulab1.dto.CreateTrackDTO;
import org.example.kjulab1.dto.TrackDTO;
import org.example.kjulab1.dto.UpdateTrackDTO;
import org.example.kjulab1.entity.Track;
import org.example.kjulab1.exception.ResourceNotFoundException;
import org.example.kjulab1.mapper.TrackMapper;
import org.example.kjulab1.repository.TrackRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrackServiceTest {

    @Mock
    private TrackRepository trackRepository;

    @Mock
    private TrackMapper trackMapper;

    @InjectMocks
    private TrackService trackService;

    @Test
    void getTrackById_shouldReturnTrackDTO() {
        Track track = new Track();
        TrackDTO dto = new TrackDTO();
        when(trackRepository.findById(1L)).thenReturn(Optional.of(track));
        when(trackMapper.toDTO(track)).thenReturn(dto);

        TrackDTO result = trackService.getTrackById(1L);

        assertNotNull(result);
        verify(trackRepository).findById(1L);
    }

    @Test
    void getTrackById_shouldThrowWhenNotFound() {
        when(trackRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> trackService.getTrackById(999L));
    }

    @Test
    void createTrack_shouldSaveAndReturnTrackDTO() {
        CreateTrackDTO createDTO = new CreateTrackDTO();
        Track track = new Track();
        TrackDTO trackDTO = new TrackDTO();

        when(trackMapper.toEntity(createDTO)).thenReturn(track);
        when(trackRepository.save(track)).thenReturn(track);
        when(trackMapper.toDTO(track)).thenReturn(trackDTO);

        TrackDTO result = trackService.createTrack(createDTO);

        assertNotNull(result);
        verify(trackRepository).save(track);
    }

    @Test
    void deleteTrack_shouldCallRepository() {
        trackService.deleteTrack(1L);
        verify(trackRepository).deleteById(1L);
    }

    @Test
    void updateTrack_shouldThrowWhenNotFound() {
        when(trackRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> trackService.updateTrack(999L, new UpdateTrackDTO()));
    }
}