package com.edu.sena.Petcare.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdateDTO {
    
    private String documentNumber;

    private String address;

    private String phone;

    private String bankAccount;

    private DocumentTypeDTO documentTypeDTO;
}
