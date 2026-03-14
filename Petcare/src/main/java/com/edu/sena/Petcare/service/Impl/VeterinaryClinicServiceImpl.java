package com.edu.sena.Petcare.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.sena.Petcare.dto.VeterinaryClinicDTO;
import com.edu.sena.Petcare.mapper.VeterinaryClinicMapper;
import com.edu.sena.Petcare.models.VeterinaryClinic;
import com.edu.sena.Petcare.repository.VeterinaryClinicRepository;
import com.edu.sena.Petcare.service.VeterinaryClinicService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VeterinaryClinicServiceImpl implements VeterinaryClinicService {

    public final VeterinaryClinicRepository veterinaryClinicRepository;
    public final VeterinaryClinicMapper veterinaryClinicMapper;
    public final com.edu.sena.Petcare.repository.UserRepository userRepository;
    public final com.edu.sena.Petcare.repository.DocumentTypeRepository documentTypeRepository;

    @Override
    @org.springframework.transaction.annotation.Transactional
    public VeterinaryClinicDTO newVeterinaryClinic(VeterinaryClinicDTO veterinaryClinicDTO) {
        // 1. Check if user already has a clinic (OneToOne constraint)
        if (veterinaryClinicDTO.getUserId() != null) {
            if (veterinaryClinicRepository.findByUser_Id(veterinaryClinicDTO.getUserId()).isPresent()) {
                throw new RuntimeException("Este usuario ya tiene una veterinaria registrada.");
            }
        }

        VeterinaryClinic veterinaryClinic = new VeterinaryClinic();
        veterinaryClinic.setAddress(veterinaryClinicDTO.getAddress());
        veterinaryClinic.setPhone(veterinaryClinicDTO.getPhone());
        veterinaryClinic.setEmail(veterinaryClinicDTO.getEmail());
        veterinaryClinic.setDocumentNumber(veterinaryClinicDTO.getDocumentNumber());
        
        if (veterinaryClinicDTO.getUserId() != null) {
            com.edu.sena.Petcare.models.User user = userRepository.findById(veterinaryClinicDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + veterinaryClinicDTO.getUserId()));
            veterinaryClinic.setUser(user);
        }

        // 2. Resolve DocumentType robustly
        if (veterinaryClinicDTO.getIdDocumentType() != null) {
            com.edu.sena.Petcare.models.DocumentType docType = documentTypeRepository.findById(veterinaryClinicDTO.getIdDocumentType())
                .or(() -> documentTypeRepository.findByAbreviation("NIT")) // fallback to NIT
                .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado y falló la búsqueda de 'NIT'"));
            veterinaryClinic.setDocumentTypeVeterinary(docType);
        } else {
             // Default to NIT if not provided
             com.edu.sena.Petcare.models.DocumentType docType = documentTypeRepository.findByAbreviation("NIT")
                .orElseThrow(() -> new RuntimeException("No se proporcionó tipo de documento y no se encontró 'NIT' en la base de datos."));
             veterinaryClinic.setDocumentTypeVeterinary(docType);
        }

        VeterinaryClinic veterinaryClinicGuardada = veterinaryClinicRepository.save(veterinaryClinic);
        VeterinaryClinicDTO result = veterinaryClinicMapper.toDTO(veterinaryClinicGuardada);
        if (veterinaryClinicGuardada.getUser() != null) {
            result.setPicture(veterinaryClinicGuardada.getUser().getPicture());
        }
        return result;
    }

    @Override
    public List<VeterinaryClinicDTO> getAllVeterinaryClinics() {
        return veterinaryClinicRepository.findAll().stream()
            .map(vc -> {
                VeterinaryClinicDTO dto = veterinaryClinicMapper.toDTO(vc);
                if (vc.getUser() != null) {
                    dto.setPicture(vc.getUser().getPicture());
                }
                return dto;
            }).toList();
    }

    @Override
    public VeterinaryClinicDTO getSpecificVeterinaryClinic(Long id) {
        VeterinaryClinic veterinaryClinic = veterinaryClinicRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("No se encontro la veterinaria por ID " + id));
        VeterinaryClinicDTO dto = veterinaryClinicMapper.toDTO(veterinaryClinic);
        if (veterinaryClinic.getUser() != null) {
            dto.setPicture(veterinaryClinic.getUser().getPicture());
        }
        return dto;

    }

    @Override
    public VeterinaryClinicDTO updateVeterinaryClinic(Long id, VeterinaryClinicDTO veterinaryClinicDTO) {
        VeterinaryClinic veterinaryClinicExiste = veterinaryClinicRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se ha encontrado la veterinaria con ID " + id + " Para su actualizacion"));

        veterinaryClinicMapper.toEntity(veterinaryClinicDTO, veterinaryClinicExiste);

        VeterinaryClinic veterinaryClinicActualizada = veterinaryClinicRepository.save(veterinaryClinicExiste);
        VeterinaryClinicDTO result = veterinaryClinicMapper.toDTO(veterinaryClinicActualizada);
        if (veterinaryClinicActualizada.getUser() != null) {
            result.setPicture(veterinaryClinicActualizada.getUser().getPicture());
        }
        return result;
    }

    @Override
    public void deleteVeterinaryClinic(Long id) {
        if(!veterinaryClinicRepository.existsById(id)){
            throw new RuntimeException("Veterinaria con ID "+id+" No encontrado para su eliminacion");
        }
        veterinaryClinicRepository.deleteById(id);
    }


}
