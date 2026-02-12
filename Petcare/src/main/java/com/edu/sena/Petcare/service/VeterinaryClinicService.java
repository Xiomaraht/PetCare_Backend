package com.edu.sena.Petcare.service;

import java.util.List;

import com.edu.sena.Petcare.dto.VeterinaryClinicDTO;

public interface VeterinaryClinicService {

    VeterinaryClinicDTO newVeterinaryClinic(VeterinaryClinicDTO veterinaryClinicDTO);

    List<VeterinaryClinicDTO> getAllVeterinaryClinics();

    VeterinaryClinicDTO getSpecificVeterinaryClinic(Long id);

    VeterinaryClinicDTO updateVeterinaryClinic(Long id, VeterinaryClinicDTO veterinaryClinicDTO);

    void deleteVeterinaryClinic(Long id);

}
