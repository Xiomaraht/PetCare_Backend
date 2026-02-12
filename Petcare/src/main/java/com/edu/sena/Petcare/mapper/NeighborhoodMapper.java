package com.edu.sena.Petcare.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edu.sena.Petcare.dto.NeighborhoodDTO;
import com.edu.sena.Petcare.models.Neighborhood;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NeighborhoodMapper {

    private final ModelMapper modelMapper;
    
    public Neighborhood toEntity(NeighborhoodDTO neighborhoodDTO){
        return modelMapper.map(neighborhoodDTO, Neighborhood.class);
    }

    public void toEntity(NeighborhoodDTO neighborhoodDTO, Neighborhood neighborhood){
        modelMapper.map(neighborhoodDTO, neighborhood);
    }

    public NeighborhoodDTO toDTO(Neighborhood neighborhood){
        return modelMapper.map(neighborhood, NeighborhoodDTO.class);
    }
}
