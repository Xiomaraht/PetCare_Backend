package com.edu.sena.Petcare.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreationDTO {

    private Long id;

    private String documentNumber;

    private String address;

    private String phone;

    private String bankAccount;

    private Long userId; //Id del usuario que vincularé a esta Entidad

    private Long documentTypeId; //Id del tipo de documento que vincularé
}
