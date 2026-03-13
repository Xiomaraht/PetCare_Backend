package com.edu.sena.Petcare.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.sena.Petcare.dto.VeterinaryClinicDTO;
import com.edu.sena.Petcare.service.VeterinaryClinicService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/veterinary-clinics")
@RequiredArgsConstructor
public class VeterinaryClinicController {

    private final VeterinaryClinicService veterinaryClinicService;

    @PostMapping
    public ResponseEntity<VeterinaryClinicDTO> crearClinicaVeterinaria(@RequestBody VeterinaryClinicDTO veterinaryClinicDTO){
        VeterinaryClinicDTO clinicaNueva = veterinaryClinicService.newVeterinaryClinic(veterinaryClinicDTO);
        return new ResponseEntity<>(clinicaNueva, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VeterinaryClinicDTO>> obtenerClinicasVeterinarias(){
        List<VeterinaryClinicDTO> clinicas = veterinaryClinicService.getAllVeterinaryClinics();
        return new ResponseEntity<>(clinicas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinaryClinicDTO> obtenerClinicaVeterinariaPorId(@PathVariable Long id){
        VeterinaryClinicDTO clinicaBuscada = veterinaryClinicService.getSpecificVeterinaryClinic(id);
        return new ResponseEntity<>(clinicaBuscada, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeterinaryClinicDTO> actualizarClinicaVeterinaria(@PathVariable Long id, @RequestBody VeterinaryClinicDTO veterinaryClinicDTO){
        VeterinaryClinicDTO actulizandoClinica = veterinaryClinicService.updateVeterinaryClinic(id, veterinaryClinicDTO);
        return new ResponseEntity<>(actulizandoClinica, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarClinicaVeterinaria(@PathVariable Long id){
        veterinaryClinicService.deleteVeterinaryClinic(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
