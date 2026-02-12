package com.edu.sena.Petcare.service;

import com.edu.sena.Petcare.dto.RaceDTO;
import com.edu.sena.Petcare.dto.SpecieDTO;
import java.util.List;

public interface DataService {
    List<SpecieDTO> getAllSpecies();
    List<RaceDTO> getRacesBySpecieId(Long specieId);
}
