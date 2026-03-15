package com.edu.sena.Petcare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeterinaryClinicDTO {

    private Long id;
    
    private String name;
    private String nit;
    private String address; 
    private String phone;
    private String email;
    private String documentNumber;
    private String description;
    private String openingHours;
    private String status;

    private Long userId;
    private Long idDocumentType;
    private String picture;

}
