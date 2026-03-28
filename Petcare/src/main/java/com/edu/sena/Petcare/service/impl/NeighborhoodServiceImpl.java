package com.edu.sena.Petcare.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.sena.Petcare.dto.NeighborhoodDTO;
import com.edu.sena.Petcare.mapper.NeighborhoodMapper;
import com.edu.sena.Petcare.models.Neighborhood;
import com.edu.sena.Petcare.repository.NeighborhoodRepository;
import com.edu.sena.Petcare.service.NeighborhoodService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NeighborhoodServiceImpl implements NeighborhoodService{
    
    private final NeighborhoodRepository neighborhoodRepository;
    private final NeighborhoodMapper neighborhoodMapper;
    
    @Override
    public List<NeighborhoodDTO> getAllNeighboors() {
        List<Neighborhood> neighborhoods = neighborhoodRepository.findAll();
        return neighborhoods.stream().map(neighborhoodMapper::toDTO).toList();
    }


}
