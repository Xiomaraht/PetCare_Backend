package com.edu.sena.Petcare.mapper;

import com.edu.sena.Petcare.dto.PetDTO;
import com.edu.sena.Petcare.dto.PetRegistrationDTO;
import com.edu.sena.Petcare.models.Pet;

public class PetMapper {

    public static Pet toEntity(PetRegistrationDTO dto) {
        Pet pet = new Pet();
        pet.setImageUrl(dto.getImageUrl());
        pet.setName(dto.getName());
        
        pet.setBirthdate(dto.getBirthdate()); 
        pet.setMicrochip(dto.getMicrochip());
        pet.setColor(dto.getColor());
        pet.setWeight(dto.getWeight());
        pet.setGender(dto.getGender());
        pet.setStatus(dto.getStatus()); 
        
        return pet;
    }

    public static PetDTO toDTO(Pet pet) {
        PetDTO dto = new PetDTO();
        dto.setId(pet.getId());
        dto.setImageUrl(pet.getImageUrl());
        dto.setName(pet.getName());
        
        dto.setBirthdate(pet.getBirthdate());
        dto.setMicrochip(pet.getMicrochip());
        dto.setColor(pet.getColor());
        dto.setWeight(pet.getWeight());
        dto.setGender(pet.getGender());
        dto.setStatus(pet.getStatus());

        if (pet.getRaza() != null) { 
            dto.setRaceId(pet.getRaza().getId());
            dto.setRaceName(pet.getRaza().getName());
            if (pet.getRaza().getEspecie() != null) {
                dto.setSpecieName(pet.getRaza().getEspecie().getName());
            }
        }

        if (pet.getCustomer() != null) { 
            dto.setCustomerId(pet.getCustomer().getId());
            dto.setCustomerName(pet.getCustomer().getName());
        }
        
        return dto;
    }
}
