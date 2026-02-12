package com.edu.sena.Petcare.Controller;

import com.edu.sena.Petcare.dto.*;
import com.edu.sena.Petcare.service.ExamsService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("exams")
@RequiredArgsConstructor
public class ExamsController {

    private final ExamsService examsService;

    @PostMapping
    public ExamsDTO create(@RequestBody ExamsRegistrationDTO dto) {
        return examsService.create(dto);
    }

    @PutMapping("/{id}")
    public ExamsDTO update(@PathVariable Long id,
                          @RequestBody ExamsRegistrationDTO dto) {
        return examsService.update(id, dto);
    }

    @GetMapping
    public List<ExamsDTO> getAll() {
        return examsService.findAll();
    }

    @GetMapping("/active")
    public List<ExamsDTO> getAllActive() {
        return examsService.findAllActive();
    }

    @GetMapping("/{id}")
    public ExamsDTO getById(@PathVariable Long id) {
        return examsService.findById(id);
    }

    @PatchMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        examsService.deactivate(id);
    }
}
