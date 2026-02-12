package com.edu.sena.Petcare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRefDTO {

    private Long id; // SOLO necesitamos el ID para buscar en el repositorio

}