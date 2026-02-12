package com.edu.sena.Petcare.mapper;

import com.edu.sena.Petcare.dto.ServicesDTO;
import com.edu.sena.Petcare.dto.ServicesRegistrationDTO; 
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

                            .map(VeterinaryClinic::getDocumentNumber) 
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
