package com.edu.sena.Petcare.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreationDTO {
    
    private Long id;
    
    private String documentNumber;

    private String address;

    private String addressDetail;

    private LocalDate birthdate;

    @NotBlank(message = "No puede estar vacio el numero de telefono")
    @Size(max = 15, message = "El numero no puede superar los 15 caracteres")
    private String phone;

    private Long userId; //Id del usuario que vincularé a esta Entidad

    private Long documentTypeId; //Id del tipo de documento que vincularé

    private Long neighborhoodId;
}
