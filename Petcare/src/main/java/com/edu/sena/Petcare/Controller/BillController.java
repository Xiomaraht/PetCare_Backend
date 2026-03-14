package com.edu.sena.Petcare.controller;

import com.edu.sena.Petcare.dto.BillDTO;
import com.edu.sena.Petcare.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @GetMapping("/clinic/{clinicId}")
    public ResponseEntity<List<BillDTO>> findByVeterinaryClinicId(@PathVariable Long clinicId) {
        return ResponseEntity.ok(billService.findByVeterinaryClinicId(clinicId));
    }
}
