package com.edu.sena.Petcare.service.Impl;

import com.edu.sena.Petcare.repository.SpecieRepository;
import com.edu.sena.Petcare.service.DataService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.edu.sena.Petcare.dto.RaceDTO;
import com.edu.sena.Petcare.dto.SpecieDTO;
import com.edu.sena.Petcare.models.Race;
import com.edu.sena.Petcare.repository.RaceRepository;

@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {
    
    private final SpecieRepository specieRepository;
    private final RaceRepository raceRepository;
    private final ModelMapper modelMapper; // Asumiendo que usas ModelMapper

    @Override
    public List<SpecieDTO> getAllSpecies() {
        return specieRepository.findAll().stream()
                .map(specie -> modelMapper.map(specie, SpecieDTO.class))
                .toList();
    }

    @Override
    public List<RaceDTO> getRacesBySpecieId(Long specieId) {
        List<Race> races = raceRepository.findByEspecie_Id(specieId); 
            return races.stream()
                .map(race -> {
                    // Mapeamos Race a RaceDTO (incluyendo SpecieDTO)
                    RaceDTO raceDTO = modelMapper.map(race, RaceDTO.class);
                    // Mapeo manual de la especie para el RaceDTO
                    if (race.getEspecie() != null) {
                        raceDTO.setEspecie(modelMapper.map(race.getEspecie(), SpecieDTO.class));
                    }
                    return raceDTO;
                })
                .toList();
    }
}
