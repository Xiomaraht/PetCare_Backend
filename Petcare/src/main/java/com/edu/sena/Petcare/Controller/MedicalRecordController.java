package com.edu.sena.Petcare.controller;

import com.edu.sena.Petcare.dto.MedicalRecordDTO;
import com.edu.sena.Petcare.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @PostMapping
    public ResponseEntity<MedicalRecordDTO> create(@RequestBody MedicalRecordDTO dto) {
        return ResponseEntity.ok(medicalRecordService.create(dto));
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<MedicalRecordDTO>> findByPetId(@PathVariable Long petId) {
        return ResponseEntity.ok(medicalRecordService.findByPetId(petId));
    }

    @GetMapping("/clinic/{clinicId}")
    public ResponseEntity<List<MedicalRecordDTO>> findByClinicId(@PathVariable Long clinicId) {
        return ResponseEntity.ok(medicalRecordService.findByClinicId(clinicId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicalRecordService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
