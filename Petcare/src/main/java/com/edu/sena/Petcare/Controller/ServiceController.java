package com.edu.sena.Petcare.controller;

import com.edu.sena.Petcare.dto.*;
import com.edu.sena.Petcare.service.ServicesService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServicesService servicesService;

    @PostMapping
    public ServicesDTO create(@RequestBody ServicesRegistrationDTO dto) {
        return servicesService.create(dto);
    }

    @PutMapping("/{id}")
    public ServicesDTO update(@PathVariable Long id,
                             @RequestBody ServicesRegistrationDTO dto) {
        return servicesService.update(id, dto);
    }

    @GetMapping
    public List<ServicesDTO> getAll() {
        return servicesService.findAll();
    }

    @GetMapping("/{id}")
    public ServicesDTO getById(@PathVariable Long id) {

        return servicesService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        servicesService.delete(id);
    }
}
