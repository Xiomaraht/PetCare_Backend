package com.edu.sena.Petcare.service;

import com.edu.sena.Petcare.dto.ServicesDTO;
import com.edu.sena.Petcare.dto.ServicesRegistrationDTO;

import java.util.List;

public interface ServicesService {

    ServicesDTO create(ServicesRegistrationDTO dto);

    ServicesDTO update(Long id, ServicesRegistrationDTO dto);

    List<ServicesDTO> findAll();

    ServicesDTO findById(Long id);

    void delete(Long id);
}
