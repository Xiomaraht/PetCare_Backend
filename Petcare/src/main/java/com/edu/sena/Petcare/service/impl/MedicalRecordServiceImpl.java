package com.edu.sena.Petcare.service.Impl;

import com.edu.sena.Petcare.dto.MedicalRecordDTO;
import com.edu.sena.Petcare.models.MedicalRecord;
import com.edu.sena.Petcare.models.Pet;
import com.edu.sena.Petcare.models.VeterinaryClinic;
import com.edu.sena.Petcare.repository.MedicalRecordRepository;
import com.edu.sena.Petcare.repository.PetRepository;
import com.edu.sena.Petcare.repository.VeterinaryClinicRepository;
import com.edu.sena.Petcare.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PetRepository petRepository;
    private final VeterinaryClinicRepository clinicRepository;

    @Override
    public MedicalRecordDTO create(MedicalRecordDTO dto) {
        Pet pet = petRepository.findById(dto.getPetId())
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        
        VeterinaryClinic clinic = null;
        if (dto.getClinicId() != null) {
            clinic = clinicRepository.findById(dto.getClinicId()).orElse(null);
        }

        MedicalRecord record = MedicalRecord.builder()
                .type(dto.getType())
                .recordDate(dto.getRecordDate())
                .description(dto.getDescription())
                .findings(dto.getFindings())
                .treatment(dto.getTreatment())
                .pet(pet)
                .veterinaryClinic(clinic)
                .build();

        record = medicalRecordRepository.save(record);
        dto.setId(record.getId());
        return dto;
    }

    @Override
    public List<MedicalRecordDTO> findByPetId(Long petId) {
        return medicalRecordRepository.findByPetId(petId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicalRecordDTO> findByClinicId(Long clinicId) {
        return medicalRecordRepository.findByVeterinaryClinicId(clinicId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        medicalRecordRepository.deleteById(id);
    }

    private MedicalRecordDTO toDTO(MedicalRecord record) {
        return MedicalRecordDTO.builder()
                .id(record.getId())
                .type(record.getType())
                .recordDate(record.getRecordDate())
                .description(record.getDescription())
                .findings(record.getFindings())
                .treatment(record.getTreatment())
                .petId(record.getPet().getId())
                .clinicId(record.getVeterinaryClinic() != null ? record.getVeterinaryClinic().getId() : null)
                .build();
    }
}
