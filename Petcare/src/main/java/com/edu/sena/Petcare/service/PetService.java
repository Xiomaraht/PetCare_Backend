package com.edu.sena.Petcare.service;

import java.util.List;

import com.edu.sena.Petcare.dto.PetDTO;
import com.edu.sena.Petcare.dto.PetRegistrationDTO;

public interface PetService {

    
    PetDTO register(PetRegistrationDTO dto);

    
    PetDTO update(Long id, PetRegistrationDTO dto); 

    
    List<PetDTO> findAll();

    
    PetDTO findById(Long id);

    
    List<PetDTO> findByCustomerId(Long customerId);

    List<PetDTO> findPetsByClinicId(Long clinicId);

    void delete(Long id);
}
