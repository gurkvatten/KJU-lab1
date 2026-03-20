package org.example.kjulab1.controller;

import org.example.kjulab1.dto.CreateTrackDTO;
import org.example.kjulab1.dto.TrackDTO;
import org.example.kjulab1.exception.ResourceNotFoundException;
import org.example.kjulab1.service.TrackService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrackController.class)
class TrackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TrackService trackService;

    private TrackDTO sampleTrack() {
        TrackDTO dto = new TrackDTO();
        dto.setId(1L);
        dto.setName("Monza");
        dto.setDescription("Temple of Speed");
        dto.setCountry("Italy");
        dto.setCity("Monza");
        dto.setLapRecord("1:21.046");
        dto.setNumberOfCorners(11);
        dto.setCircuitLengthKm(5.793);
        return dto;
    }

    @Test
    void listTracks_shouldReturnListView() throws Exception {
        when(trackService.filterTracks(isNull(), isNull(), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(sampleTrack())));

        mockMvc.perform(get("/tracks"))
                .andExpect(status().isOk())
                .andExpect(view().name("tracks/list"))
                .andExpect(model().attributeExists("tracks"));
    }

    @Test
    void showCreateForm_shouldReturnCreateView() throws Exception {
        mockMvc.perform(get("/tracks/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("tracks/create"))
                .andExpect(model().attributeExists("track"));
    }

    @Test
    void showEditForm_shouldReturnEditView() throws Exception {
        when(trackService.getTrackById(1L)).thenReturn(sampleTrack());

        mockMvc.perform(get("/tracks/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("tracks/edit"))
                .andExpect(model().attributeExists("track"));
    }

    @Test
    void showEditForm_shouldReturnErrorViewWhenNotFound() throws Exception {
        when(trackService.getTrackById(999L))
                .thenThrow(new ResourceNotFoundException("Track with id 999 not found"));

        mockMvc.perform(get("/tracks/999/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("errorMessage"));
    }

    @Test
    void createTrack_shouldRedirectOnSuccess() throws Exception {
        when(trackService.createTrack(any(CreateTrackDTO.class))).thenReturn(sampleTrack());

        mockMvc.perform(post("/tracks")
                        .param("name", "Monza")
                        .param("description", "Temple of Speed")
                        .param("country", "Italy")
                        .param("city", "Monza")
                        .param("lapRecord", "1:21.046")
                        .param("numberOfCorners", "11")
                        .param("circuitLengthKm", "5.793"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tracks"));
    }

    @Test
    void createTrack_shouldReturnCreateViewOnValidationError() throws Exception {
        mockMvc.perform(post("/tracks")
                        .param("name", "")
                        .param("description", "")
                        .param("country", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("tracks/create"));
    }
}