package com.edu.sena.Petcare.dto;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecordDTO {
    private Long id;
    private String type;
    private LocalDate recordDate;
    private String description;
    private String findings;
    private String treatment;
    private Long petId;
    private Long clinicId;
}
