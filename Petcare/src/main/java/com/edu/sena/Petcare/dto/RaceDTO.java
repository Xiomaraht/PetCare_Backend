package com.edu.sena.Petcare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceDTO {
    private Long id;
    private String name;
    // Incluir la especie asociada
    private SpecieDTO especie; 
}
