package org.example.kjulab1.controller;

import org.example.kjulab1.dto.CreateTrackDTO;
import org.example.kjulab1.dto.UpdateTrackDTO;
import org.example.kjulab1.service.TrackService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/tracks")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping
    public String listTracks(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {

        if (country != null && country.isBlank()) country = null;
        if (city != null && city.isBlank()) city = null;

        Pageable pageable = PageRequest.of(page, size);
        var trackPage = trackService.filterTracks(country, city, pageable);

        model.addAttribute("tracks", trackPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", trackPage.getTotalPages());
        model.addAttribute("country", country);
        model.addAttribute("city", city);
        return "tracks/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("track", new CreateTrackDTO());
        return "tracks/create";
    }

    @PostMapping
    public String createTrack(@Valid @ModelAttribute("track") CreateTrackDTO dto,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "tracks/create";
        }
        trackService.createTrack(dto);
        return "redirect:/tracks";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("track", trackService.getTrackById(id));
        return "tracks/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateTrack(@PathVariable Long id,
                              @Valid @ModelAttribute("track") UpdateTrackDTO dto,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "tracks/edit";
        }
        trackService.updateTrack(id, dto);
        return "redirect:/tracks";
    }

    @PostMapping("/{id}/delete")
    public String deleteTrack(@PathVariable Long id) {
        trackService.deleteTrack(id);
        return "redirect:/tracks";
    }
}