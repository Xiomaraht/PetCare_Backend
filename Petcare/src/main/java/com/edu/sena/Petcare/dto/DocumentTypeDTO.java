package com.edu.sena.Petcare.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTypeDTO {
    
    private Long id;

    private String name;

    private String abreviation;
}
