package com.edu.sena.Petcare.dto;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetDTO {
    
    private Long id;
    private String imageUrl;
    private String name;
    private LocalDate birthdate; 
    private String microchip;
    private String color;
    private String weight;
    private String gender;

    
    private Boolean status; 
    private Long raceId;
    private Long customerId; 
}
