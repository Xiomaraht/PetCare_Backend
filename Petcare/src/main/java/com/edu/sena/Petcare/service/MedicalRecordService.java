package com.edu.sena.Petcare.service;

import com.edu.sena.Petcare.dto.MedicalRecordDTO;
import java.util.List;

public interface MedicalRecordService {
    MedicalRecordDTO create(MedicalRecordDTO dto);
    List<MedicalRecordDTO> findByPetId(Long petId);
    List<MedicalRecordDTO> findByClinicId(Long clinicId);
    void delete(Long id);
}
