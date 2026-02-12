package com.edu.sena.Petcare.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.sena.Petcare.dto.NeighborhoodDTO;
import com.edu.sena.Petcare.service.NeighborhoodService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/neighborhoods")
@RequiredArgsConstructor
public class NeighborhoodController {

    private final NeighborhoodService neighborhoodService;

    @GetMapping
    public ResponseEntity<List<NeighborhoodDTO>> getAllNeighborhoods() {
        List<NeighborhoodDTO> neighborhoods = neighborhoodService.getAllNeighboors();
        return ResponseEntity.ok(neighborhoods);
    }
}
