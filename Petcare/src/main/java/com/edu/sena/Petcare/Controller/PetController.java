package com.edu.sena.Petcare.controller;

import com.edu.sena.Petcare.dto.PetDTO;
import com.edu.sena.Petcare.dto.PetRegistrationDTO;
import com.edu.sena.Petcare.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    public PetDTO register(@RequestBody PetRegistrationDTO dto) {
        return petService.register(dto);
    }

    @PostMapping("/customer/{customerId}")
    public PetDTO registerForCustomer(@PathVariable Long customerId, @RequestBody PetRegistrationDTO dto) {
        dto.setCustomerId(customerId);
        return petService.register(dto);
    }

    @GetMapping
    public List<PetDTO> getAll() {
        return petService.findAll();
    }

    @GetMapping("/{id}")
    public PetDTO getById(@PathVariable Long id) {
        return petService.findById(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<PetDTO> getByCustomerId(@PathVariable Long customerId) {
        return petService.findByCustomerId(customerId);
    }

    @GetMapping("/clinic/{clinicId}")
    public List<PetDTO> getPetsByClinic(@PathVariable Long clinicId) {
        return petService.findPetsByClinicId(clinicId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        petService.delete(id);
    }

    @PutMapping("/{id}")
    public PetDTO update(@PathVariable Long id, @RequestBody PetRegistrationDTO dto) {
        return petService.update(id, dto);
    }

    @GetMapping("/cleanup")
    public String cleanup() {
        petService.cleanupFailedRegistrations();
        return "Cleanup completed. Pets with empty microchips have been removed.";
    }

    @GetMapping("/debug")
    public List<PetDTO> debug() {
        return petService.findAllDetailed();
    }

    @GetMapping("/purge")
    public String purge() {
        // Borrar TODO para empezar de cero si el usuario quiere
        petService.findAllDetailed().forEach(p -> petService.delete(p.getId()));
        return "Purge completed. All pets have been removed.";
    }
}
