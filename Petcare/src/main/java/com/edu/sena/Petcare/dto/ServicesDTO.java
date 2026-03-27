package com.edu.sena.Petcare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List; 

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicesDTO {

    private Long id;

    private Boolean status;

    private String name;

    private String description;

    private String picture;

    private List<VeterinaryClinicDTO> veterinaryClinics; 
}
