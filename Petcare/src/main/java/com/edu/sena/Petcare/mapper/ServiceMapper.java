package com.edu.sena.Petcare.mapper;

import com.edu.sena.Petcare.dto.ServicesDTO;
import com.edu.sena.Petcare.dto.ServicesRegistrationDTO; 
import com.edu.sena.Petcare.dto.VeterinaryClinicDTO;
import com.edu.sena.Petcare.models.Services; 
import com.edu.sena.Petcare.models.VeterinaryClinic;

import java.util.stream.Collectors;
import java.util.ArrayList;

public class ServiceMapper {

    public static ServicesDTO toDTO(Services service) { 
        ServicesDTO dto = new ServicesDTO();
        
        dto.setId(service.getId());
        dto.setName(service.getName());
        dto.setDescription(service.getDescription());
        dto.setPicture(service.getPicture());
        dto.setStatus(service.getStatus());
        
        if (service.getVeterinaryClinics() != null) {
            dto.setVeterinaryClinics(
                    service.getVeterinaryClinics()
                            .stream()
                            .filter(clinic -> clinic != null)
                            .map(clinic -> {
                                VeterinaryClinicDTO clinicDto = new VeterinaryClinicDTO();
                                clinicDto.setId(clinic.getId());
                                clinicDto.setName(clinic.getName());
                                clinicDto.setNit(clinic.getNit());
                                clinicDto.setAddress(clinic.getAddress());
                                clinicDto.setPhone(clinic.getPhone());
                                clinicDto.setEmail(clinic.getEmail());
                                clinicDto.setDocumentNumber(clinic.getDocumentNumber());
                                clinicDto.setPicture(clinic.getPicture());
                                return clinicDto;
                            }) 
                            .collect(Collectors.toList())
            );
        } else {
            dto.setVeterinaryClinics(new ArrayList<>());
        }
        
        return dto;
    }

    public static Services toEntity(ServicesRegistrationDTO dto) {
        Services service = new Services();
        
        service.setName(dto.getName());
        service.setDescription(dto.getDescription());
        service.setPicture(dto.getPicture());
        service.setStatus(dto.getStatus());
        
        return service;
    }
}
