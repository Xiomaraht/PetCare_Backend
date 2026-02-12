package com.edu.sena.Petcare.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edu.sena.Petcare.dto.VeterinaryClinicDTO;
import com.edu.sena.Petcare.models.VeterinaryClinic;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class VeterinaryClinicMapper {

    private final ModelMapper modelMapper;

    public VeterinaryClinic toEntity(VeterinaryClinicDTO veterinaryClinicDTO){
        return modelMapper.map(veterinaryClinicDTO, VeterinaryClinic.class);
    }

    public void toEntity(VeterinaryClinicDTO veterinaryClinicDTO, VeterinaryClinic userExistente){
        modelMapper.map(veterinaryClinicDTO, userExistente);
    }

    public VeterinaryClinicDTO toDTO(VeterinaryClinic veterinaryClinicDTO){
        return modelMapper.map(veterinaryClinicDTO, VeterinaryClinicDTO.class);
    }

}
