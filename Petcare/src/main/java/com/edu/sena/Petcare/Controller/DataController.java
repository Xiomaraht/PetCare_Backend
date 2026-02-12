package com.edu.sena.Petcare.Controller;

import com.edu.sena.Petcare.dto.RaceDTO;
import com.edu.sena.Petcare.dto.SpecieDTO;
import com.edu.sena.Petcare.service.DataService; 
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data") 
@RequiredArgsConstructor
public class DataController {

    private final DataService dataService;


    @GetMapping("/species")
    public ResponseEntity<List<SpecieDTO>> getAllSpecies(){
        List<SpecieDTO> species = dataService.getAllSpecies();
        return new ResponseEntity<>(species, HttpStatus.OK);
    }

    @GetMapping("/races/species/{specieId}")
    public ResponseEntity<List<RaceDTO>> getRacesBySpecieId(@PathVariable Long specieId){
        List<RaceDTO> races = dataService.getRacesBySpecieId(specieId);
        return new ResponseEntity<>(races, HttpStatus.OK);
    }
}

