package com.edu.sena.Petcare.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    private String documentNumber;

    private String documentTypeName;

    private String phone;


}
