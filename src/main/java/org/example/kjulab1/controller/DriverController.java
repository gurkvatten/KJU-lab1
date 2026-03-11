package org.example.kjulab1.controller;

import org.example.kjulab1.dto.CreateDriverDTO;
import org.example.kjulab1.dto.UpdateDriverDTO;
import org.example.kjulab1.service.DriverService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public String listDrivers(Model model) {
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "drivers/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("driver", new CreateDriverDTO());
        return "drivers/create";
    }

    @PostMapping
    public String createDriver(@Valid @ModelAttribute("driver") CreateDriverDTO dto,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "drivers/create";
        }
        driverService.createDriver(dto);
        return "redirect:/drivers";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("driver", driverService.getDriverById(id));
        return "drivers/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateDriver(@PathVariable Long id,
                               @Valid @ModelAttribute("driver") UpdateDriverDTO dto,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "drivers/edit";
        }
        driverService.updateDriver(id, dto);
        return "redirect:/drivers";
    }

    @PostMapping("/{id}/delete")
    public String deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return "redirect:/drivers";
    }
}