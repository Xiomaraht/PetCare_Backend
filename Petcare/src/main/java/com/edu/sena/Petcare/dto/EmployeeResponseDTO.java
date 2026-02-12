package com.edu.sena.Petcare.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO {

    private Long id;

    private String documentNumber;

    private String address;

    private String phone;

    private UserGetDTO user;
}
