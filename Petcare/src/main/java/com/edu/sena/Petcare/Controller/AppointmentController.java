package com.edu.sena.Petcare.controller;

import com.edu.sena.Petcare.dto.AppointmentDTO;
import com.edu.sena.Petcare.dto.AppointmentRegistrationDTO;
import com.edu.sena.Petcare.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentDTO> create(@RequestBody AppointmentRegistrationDTO appointmentDto) {
        return ResponseEntity.ok(appointmentService.createFromDto(appointmentDto));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> findAll() {
        return ResponseEntity.ok(appointmentService.findAll());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<AppointmentDTO>> findByCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.findByCustomerId(id));
    }

    @GetMapping("/clinic/{id}")
    public ResponseEntity<List<AppointmentDTO>> findByClinic(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.findByClinicId(id));
    }

    @GetMapping("/pet/{id}")
    public ResponseEntity<List<AppointmentDTO>> findByPet(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.findByPetId(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AppointmentDTO> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(appointmentService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
