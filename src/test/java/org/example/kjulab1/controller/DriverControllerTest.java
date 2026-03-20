package org.example.kjulab1.controller;

import org.example.kjulab1.dto.CreateDriverDTO;
import org.example.kjulab1.dto.DriverDTO;
import org.example.kjulab1.exception.ResourceNotFoundException;
import org.example.kjulab1.service.DriverService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DriverController.class)
class DriverControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DriverService driverService;

    private DriverDTO sampleDriver() {
        DriverDTO dto = new DriverDTO();
        dto.setId(1L);
        dto.setFirstName("Lewis");
        dto.setLastName("Hamilton");
        dto.setDescription("Seven-time champion");
        dto.setDateOfBirth(LocalDate.of(1985, 1, 7));
        dto.setNationality("British");
        dto.setTeamName("Ferrari");
        dto.setDriverNumber(44);
        dto.setChampionships(7);
        return dto;
    }

    @Test
    void listDrivers_shouldReturnListView() throws Exception {
        when(driverService.filterDrivers(isNull(), isNull(), isNull(), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(sampleDriver())));

        mockMvc.perform(get("/drivers"))
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/list"))
                .andExpect(model().attributeExists("drivers"));
    }

    @Test
    void showCreateForm_shouldReturnCreateView() throws Exception {
        mockMvc.perform(get("/drivers/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/create"))
                .andExpect(model().attributeExists("driver"));
    }

    @Test
    void showEditForm_shouldReturnEditView() throws Exception {
        when(driverService.getDriverById(1L)).thenReturn(sampleDriver());

        mockMvc.perform(get("/drivers/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/edit"))
                .andExpect(model().attributeExists("driver"));
    }

    @Test
    void showEditForm_shouldReturnErrorViewWhenNotFound() throws Exception {
        when(driverService.getDriverById(999L))
                .thenThrow(new ResourceNotFoundException("Driver with id 999 not found"));

        mockMvc.perform(get("/drivers/999/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("errorMessage"));
    }

    @Test
    void createDriver_shouldRedirectOnSuccess() throws Exception {
        when(driverService.createDriver(any(CreateDriverDTO.class))).thenReturn(sampleDriver());

        mockMvc.perform(post("/drivers")
                        .param("firstName", "Lewis")
                        .param("lastName", "Hamilton")
                        .param("description", "Seven-time champion")
                        .param("dateOfBirth", "1985-01-07")
                        .param("nationality", "British")
                        .param("teamName", "Ferrari")
                        .param("driverNumber", "44")
                        .param("championships", "7"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/drivers"));
    }

    @Test
    void createDriver_shouldReturnCreateViewOnValidationError() throws Exception {
        mockMvc.perform(post("/drivers")
                        .param("firstName", "")
                        .param("lastName", "")
                        .param("description", "")
                        .param("driverNumber", "44")
                        .param("championships", "7"))
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/create"));
    }
}