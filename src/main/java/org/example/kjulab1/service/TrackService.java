package org.example.kjulab1.service;

import org.example.kjulab1.dto.CreateTrackDTO;
import org.example.kjulab1.dto.TrackDTO;
import org.example.kjulab1.dto.UpdateTrackDTO;
import org.example.kjulab1.entity.Track;
import org.example.kjulab1.exception.ResourceNotFoundException;
import org.example.kjulab1.mapper.TrackMapper;
import org.example.kjulab1.repository.TrackRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TrackService {

    private final TrackRepository trackRepository;
    private final TrackMapper trackMapper;

    public TrackService(TrackRepository trackRepository, TrackMapper trackMapper) {
        this.trackRepository = trackRepository;
        this.trackMapper = trackMapper;
    }

    public Page<TrackDTO> filterTracks(String country, String city, Pageable pageable) {
        return trackRepository.filterTracks(country, city, pageable)
                .map(trackMapper::toDTO);
    }

    public TrackDTO getTrackById(Long id) {
        Track track = trackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Track with id " + id + " not found"));
        return trackMapper.toDTO(track);
    }

    public TrackDTO createTrack(CreateTrackDTO dto) {
        Track track = trackMapper.toEntity(dto);
        return trackMapper.toDTO(trackRepository.save(track));
    }

    public TrackDTO updateTrack(Long id, UpdateTrackDTO dto) {
        Track track = trackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Track with id " + id + " not found"));
        trackMapper.updateEntity(dto, track);
        return trackMapper.toDTO(trackRepository.save(track));
    }

    public void deleteTrack(Long id) {
        trackRepository.deleteById(id);
    }
}