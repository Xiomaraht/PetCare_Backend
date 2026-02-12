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

    @Override
    public VeterinaryClinicDTO newVeterinaryClinic(VeterinaryClinicDTO veterinaryClinicDTO) {
        VeterinaryClinic veterinaryClinic = veterinaryClinicMapper.toEntity(veterinaryClinicDTO);
        VeterinaryClinic veterinaryClinicGuardada = veterinaryClinicRepository.save(veterinaryClinic);
        return veterinaryClinicMapper.toDTO(veterinaryClinicGuardada);
    }

    @Override
    public List<VeterinaryClinicDTO> getAllVeterinaryClinics() {
        List<VeterinaryClinic> veterinarias = veterinaryClinicRepository.findAll();
        return veterinarias.stream().map(veterinaryClinicMapper::toDTO).toList();
    }

    @Override
    public VeterinaryClinicDTO getSpecificVeterinaryClinic(Long id) {
        VeterinaryClinic veterinaryClinic = veterinaryClinicRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("No se encontro la veterinaria por ID " + id));
        return veterinaryClinicMapper.toDTO(veterinaryClinic);

    }

    @Override
    public VeterinaryClinicDTO updateVeterinaryClinic(Long id, VeterinaryClinicDTO veterinaryClinicDTO) {
        VeterinaryClinic veterinaryClinicExiste = veterinaryClinicRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se ha encontrado la veterinaria con ID " + id + " Para su actualizacion"));

        veterinaryClinicMapper.toEntity(veterinaryClinicDTO, veterinaryClinicExiste);

        VeterinaryClinic veterinaryClinicActualizada = veterinaryClinicRepository.save(veterinaryClinicExiste);
        return veterinaryClinicMapper.toDTO(veterinaryClinicActualizada);
    }

    @Override
    public void deleteVeterinaryClinic(Long id) {
        if(!veterinaryClinicRepository.existsById(id)){
            throw new RuntimeException("Veterinaria con ID "+id+" No encontrado para su eliminacion");
        }
        veterinaryClinicRepository.deleteById(id);
    }


}
