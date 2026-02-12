package com.edu.sena.Petcare.service;

import com.edu.sena.Petcare.dto.ExamsDTO;
import com.edu.sena.Petcare.dto.ExamsRegistrationDTO; 

import java.util.List;

public interface ExamsService {

    ExamsDTO create(ExamsRegistrationDTO dto); 

    ExamsDTO update(Long id, ExamsRegistrationDTO dto); 

    List<ExamsDTO> findAll();

    List<ExamsDTO> findAllActive();

    ExamsDTO findById(Long id);

    void deactivate(Long id);

    void activate(Long id);
}
